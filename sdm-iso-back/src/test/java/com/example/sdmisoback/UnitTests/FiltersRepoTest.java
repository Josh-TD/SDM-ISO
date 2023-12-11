package com.example.sdmisoback.UnitTests;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.sdmisoback.BackendServerApp;
import com.example.sdmisoback.dto.FileViewDTO;
import com.example.sdmisoback.dto.FiltersDTO;
import com.example.sdmisoback.repository.FiltersRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = BackendServerApp.class)
@TestPropertySource(locations = "classpath:test.properties")
public class FiltersRepoTest {

    @Autowired
    FiltersRepo filtersRepo;

    private static String sortByCreateDate = "af.createDate";
    private static String sortByProjectName = "pj.projectName";
    private static String sortByCustomerName = "c.customerName";
    private static String sortByFileName = "af.fileName";
    private static PageRequest defaultPageRequest = PageRequest.of(0, 10);

    private static Double TOTAL_NUM_ELEMENTS = 142.0;

    /**
     * Sanity Check that the first page actually behaves like a first page
     * 
     * @param r Page result from the Repo call
     * @throws AssertionError If any of the tests fail
     */
    private void runFirstPageTests(Page<FileViewDTO> r) throws AssertionError {
        Assertions.assertTrue(r.getNumber() == 0, "Not on Page 0");
        Assertions.assertFalse(r.hasPrevious(), "First Page has Previous");
    }

    /**
     * Tests to make sure that Page Size behavior is acting as expected and that
     * the content returned also follows those rules
     * 
     * @param r                Page result from the repository call
     * @param ExpectedPageSize Page size that the test expects to be returned
     * @throws AssertionError If any of the tests fail
     */
    private void runNotLastPageTests(Page<FileViewDTO> r, int ExpectedPageSize) throws AssertionError {
        Assertions.assertTrue(r.getSize() == ExpectedPageSize, "Not Page Size " + ExpectedPageSize);
        Assertions.assertTrue(r.getNumberOfElements() == ExpectedPageSize,
                "Page elements don't add up to " + ExpectedPageSize);
        Assertions.assertFalse(r.getContent().isEmpty(), "Content is empty");
        Assertions.assertTrue(r.getContent().size() == ExpectedPageSize, "Content is not of size " + ExpectedPageSize);
    }

    @Test
    public void FiltersRepo_NoFilters_FirstPageIsValid() {
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .build();

        Page<FileViewDTO> r = filtersRepo.filterAttachments(filters);
        List<FileViewDTO> c = r.getContent();

        Assertions.assertTrue(r.isFirst(), "Page that is first is not first");
        if (r.isFirst())
            runFirstPageTests(r);

        Assertions.assertFalse(r.isLast(), "Page that is not last is last");
        if (!r.isLast())
            runNotLastPageTests(r, 10);

        Assertions.assertFalse(r.isEmpty(), "Page is empty when it shouldn't be");
        Assertions.assertTrue(r.getTotalPages() == Math.ceil(TOTAL_NUM_ELEMENTS / 10),
                "Total Pages expected: " + (Math.ceil(TOTAL_NUM_ELEMENTS / 10) + " but was: " + r.getTotalPages()));
        Assertions.assertTrue(r.getTotalElements() == TOTAL_NUM_ELEMENTS, "Total num search results is off");

        // Sort test
        boolean firstPageSorted = true;
        LocalDateTime prevFileDate = null;
        for (FileViewDTO file : c) {
            if (prevFileDate == null) {
                prevFileDate = file.fileCreateDate;
                continue;
            }
            if (file.fileCreateDate.compareTo(prevFileDate) < 0) {
                firstPageSorted = false;
                break;
            }
            prevFileDate = file.fileCreateDate;
        }
        Assertions.assertTrue(firstPageSorted, "first page is not sorted correctly");
    }

    @Test
    public void FiltersRepo_NoFilters_FullTest() {
        boolean isLast = false;
        int pageNum = 0;
        LocalDateTime prevFileDate = null;
        while (!isLast) {
            FiltersDTO filters = FiltersDTO.builder()
                    .pr(PageRequest.of(pageNum, 10))
                    .sortBy(sortByCreateDate)
                    .sortAsc(true)
                    .build();

            Page<FileViewDTO> r = filtersRepo.filterAttachments(filters);
            List<FileViewDTO> c = r.getContent();

            if (r.isFirst()) {
                runFirstPageTests(r);
                Assertions.assertTrue(r.getTotalPages() == Math.ceil(TOTAL_NUM_ELEMENTS / 10),
                        "Total Pages expected: "
                                + (Math.ceil(TOTAL_NUM_ELEMENTS / 10) + " but was: " + r.getTotalPages()));
                Assertions.assertTrue(r.getTotalElements() == TOTAL_NUM_ELEMENTS, "Total num search results is off");
            }

            if (!r.isLast()) {
                runNotLastPageTests(r, 10);
            } else {
                isLast = true;
            }

            Assertions.assertFalse(r.isEmpty(), "Page is empty when it shouldn't be");

            boolean firstPageSorted = true;
            for (FileViewDTO file : c) {
                if (prevFileDate == null) {
                    prevFileDate = file.fileCreateDate;
                    continue;
                }
                if (file.fileCreateDate.compareTo(prevFileDate) < 0) {
                    firstPageSorted = false;
                    break;
                }
                prevFileDate = file.fileCreateDate;
            }
            Assertions.assertTrue(firstPageSorted, "Pages not Sorted correctly");
            pageNum++;
        }
    }
}
