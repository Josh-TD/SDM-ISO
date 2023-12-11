package com.example.sdmisoback.UnitTests;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import jakarta.validation.constraints.AssertTrue;

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

    @Test
    public void FiltersRepo_File_Description_Test(){
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .fileDescription("Notes")
                .build();

        Page<FileViewDTO> r = filtersRepo.filterAttachments(filters);
        List<FileViewDTO> c = r.getContent();

        boolean fileTest = true;
        int id = 0;
        for (FileViewDTO file : c) {
            id = file.attachmentId;
            if (id!=1002 && id!=1029 && id!= 1052 && id!=1053 && id!=1043){
                fileTest = false;
                break;
            }
        }
        Assertions.assertTrue(fileTest, "id doesn't match: "+ id);
    }
    @Test
    public void FiltersRepo_File_Id_Test(){
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .fileId(1001)
                .build();
        
        FileViewDTO file = filtersRepo.filterAttachments(filters).getContent().get(0);
        Assertions.assertEquals(file.fileName, "Project docs.zip", "filename was "+file.fileName);
    }
    @Test
    public void FiltersRepo_File_Name_Test(){
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .fileName("Project")
                .build();
        
        List<FileViewDTO> c = filtersRepo.filterAttachments(filters).getContent();
        boolean fileTest = true;
        int id = 0;
        for (FileViewDTO file : c) {
            id = file.attachmentId;
            if (id!=1001 && id!=1005 && id!= 1006){
                fileTest = false;
                break;
            }
        }
        Assertions.assertTrue(fileTest, "id doesn't match: "+ id);
    }   
    @Test
    public void FiltersRepo_File_Create_Date_Test(){
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .createdSince(LocalDateTime.of(2008, 2, 25, 15, 16))
                .build();
        
        FileViewDTO file = filtersRepo.filterAttachments(filters).getContent().get(0);
        
        Assertions.assertEquals(file.attachmentId,1001, "id doesn't match: "+ file.attachmentId);
    }
    @Test
    public void FiltersRepo_File_Types_Test(){
        List<String> types = new ArrayList<>();
        types.add("zip");
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .fileTypes(types)
                .build();

        Page<FileViewDTO> r = filtersRepo.filterAttachments(filters);
        List<FileViewDTO> c = r.getContent();

        boolean fileTest = true;
        int id = 0;
        for (FileViewDTO file : c) {
            id = file.attachmentId;
            if (id!=1001 && id!=1013 && id!= 1016 && id!=1017 && id!=1049 && id!=1050 && id!=1051){
                fileTest = false;
                break;
            }
        }
        Assertions.assertTrue(fileTest, "id doesn't match: "+ id);
    }  
    @Test
    public void FiltersRepo_Attach_Types_Test(){
        List<String> types = new ArrayList<>();
        types.add("QP.IMPORT.EXPORT_CONTRACT");
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .attachTypes(types)
                .build();

        Page<FileViewDTO> r = filtersRepo.filterAttachments(filters);
        List<FileViewDTO> c = r.getContent();

        boolean fileTest = true;
        int id = 0;
        for (FileViewDTO file : c) {
            id = file.attachmentId;
            if (id!=1014 && id!=1022 && id!=1039){
                fileTest = false;
                break;
            }
        }
        Assertions.assertTrue(fileTest, "id doesn't match: "+ id);
    }  
    @Test
    public void FiltersRepo_Proposal_Id_Test(){
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .proposalId(222)
                .build();
        
        List<FileViewDTO> c = filtersRepo.filterAttachments(filters).getContent();
        boolean fileTest = true;
        int id = 0;
        for (FileViewDTO file : c) {
            id = file.attachmentId;
            if (id!=1002 && id!=1003 && id!= 1004 && id!= 1021 && id!= 1038){
                fileTest = false;
                break;
            }
        }
        Assertions.assertTrue(fileTest, "id doesn't match: "+ id);
    }
    @Test
    public void FiltersRepo_Proposal_Label_Test(){
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .proposalLabel("10")
                .build();
        
        List<FileViewDTO> c = filtersRepo.filterAttachments(filters).getContent();
        boolean fileTest = true;
        int id = 0;
        for (FileViewDTO file : c) {
            id = file.attachmentId;
            if (id!=1001 && id!=1018){
                fileTest = false;
                break;
            }
        }
        Assertions.assertTrue(fileTest, "id doesn't match: "+ id);
    }
    @Test
    public void FiltersRepo_Proposal_Period_Id_Test(){
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .propPeriodId(1)
                .build();
        
        List<FileViewDTO> c = filtersRepo.filterAttachments(filters).getContent();
        boolean fileTest = true;
        int id = 0;
        for (FileViewDTO file : c) {
            id = file.attachmentId;
            if (id!=1001 && id!=1018){
                fileTest = false;
                break;
            }
        }
        Assertions.assertTrue(fileTest, "id doesn't match: "+ id);
    }
    @Test
    public void FiltersRepo_Proposal_Period_Type_Test(){
        List<String> types = new ArrayList<>();
        types.add("COMMITMENT");
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .propPeriodTypes(types)
                .build();
        
        List<FileViewDTO> c = filtersRepo.filterAttachments(filters).getContent();
        Assertions.assertTrue(!c.isEmpty());
    }
    @Test
    public void FiltersRepo_Proposal_Period_Descript_Test(){
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .propPeriodDesc("201")
                .build();
        
        List<FileViewDTO> c = filtersRepo.filterAttachments(filters).getContent();
        Assertions.assertTrue(!c.isEmpty());
    }
    @Test
    public void FiltersRepo_Proposal_Period_Begin_End_Test(){
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .propPeriodBeginDate(LocalDateTime.of(2010,6,1,0,0,0))
                .propPeriodEndDate(LocalDateTime.of(2011,6,1,0,0,0))
                .build();
        
        List<FileViewDTO> c = filtersRepo.filterAttachments(filters).getContent();
        Assertions.assertTrue(!c.isEmpty());
    }
    @Test
    public void FiltersRepo_Project_Id_Test(){
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .projectId(5000)
                .build();
        
        List<FileViewDTO> c = filtersRepo.filterAttachments(filters).getContent();
        boolean fileTest = true;
        int id = 0;
        for (FileViewDTO file : c) {
            id = file.attachmentId;
            if (id!=1005 && id!=1006 && id!=1007 && id!=1026 && id!=1027 && id!=1028 && id!=1043 && id!=1044 && id!=1045){
                fileTest = false;
                break;
            }
        }
        Assertions.assertTrue(fileTest, "id doesn't match: "+ id);
    }
    @Test
    public void FiltersRepo_Project_Name_Test(){
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .projectName("New")
                .build();
        
        List<FileViewDTO> c = filtersRepo.filterAttachments(filters).getContent();
        boolean fileTest = true;
        int id = 0;
        for (FileViewDTO file : c) {
            id = file.attachmentId;
            if (id!=1005 && id!=1006 && id!=1007 && id!=1026 && id!=1027 && id!=1028 && id!=1043 && id!=1044 && id!=1045){
                fileTest = false;
                break;
            }
        }
        Assertions.assertTrue(fileTest, "id doesn't match: "+ id);
    }
    @Test
    public void FiltersRepo_Project_Types_Test(){
        List<String> types = new ArrayList<>();
        types.add("NEW_IMPORT");
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .projectTypes(types)
                .build();
        
        List<FileViewDTO> c = filtersRepo.filterAttachments(filters).getContent();
        boolean fileTest = true;
        int id = 0;
        for (FileViewDTO file : c) {
            id = file.attachmentId;
            if (id!=1005 && id!=1006 && id!=1007 && id!=1026 && id!=1027 && id!=1028 && id!=1043 && id!=1044 && id!=1045){
                fileTest = false;
                break;
            }
        }
        Assertions.assertTrue(fileTest, "id doesn't match: "+ id);
    }
    @Test
    public void FiltersRepo_Customer_Id_Test(){
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .projectId(30)
                .build();
        
        List<FileViewDTO> c = filtersRepo.filterAttachments(filters).getContent();
        boolean fileTest = true;
        int id = 0;
        for (FileViewDTO file : c) {
            id = file.attachmentId;
            if (id!=1005 && id!=1006 && id!=1007 && id!=1026 && id!=1027 && id!=1028 && id!=1043 && id!=1044 && id!=1045){
                fileTest = false;
                break;
            }
        }
        Assertions.assertTrue(fileTest, "id doesn't match: "+ id);
    }
    @Test
    public void FiltersRepo_Customer_Name_Test(){
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .customerName("Energy")
                .build();
        
        List<FileViewDTO> c = filtersRepo.filterAttachments(filters).getContent();
        boolean fileTest = true;
        int id = 0;
        for (FileViewDTO file : c) {
            id = file.attachmentId;
            if (id!=1005 && id!=1006 && id!=1007 && id!=1026 && id!=1027 && id!=1028 && id!=1043 && id!=1044 && id!=1045){
                fileTest = false;
                break;
            }
        }
        Assertions.assertTrue(fileTest, "id doesn't match: "+ id);
    }
    @Test
    public void FiltersRepo_Resource_Id_Test(){
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .resourceId(400)
                .build();
        
        List<FileViewDTO> c = filtersRepo.filterAttachments(filters).getContent();
        boolean fileTest = true;
        int id = 0;
        for (FileViewDTO file : c) {
            id = file.attachmentId;
            if (id!=1005 && id!=1006 && id!=1007 && id!=1026 && id!=1027 && id!=1028 && id!=1043 && id!=1044 && id!=1045){
                fileTest = false;
                break;
            }
        }
        Assertions.assertTrue(fileTest, "id doesn't match: "+ id);
    }
    @Test
    public void FiltersRepo_Resource_Types_Test(){
        List<String> types = new ArrayList<>();
        types.add("IMPORT");
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .resourceTypes(types)
                .build();
        
        List<FileViewDTO> c = filtersRepo.filterAttachments(filters).getContent();
        boolean fileTest = true;
        int id = 0;
        for (FileViewDTO file : c) {
            id = file.attachmentId;
            if (id!=1005 && id!=1006 && id!=1007 && id!=1026 && id!=1027 && id!=1028 && id!=1043 && id!=1044 && id!=1045){
                fileTest = false;
                break;
            }
        }
        Assertions.assertTrue(fileTest, "id doesn't match: "+ id);
    }
    @Test
    public void FiltersRepo_Auction_Id_Test(){
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .propPeriodId(1)
                .build();
        
        List<FileViewDTO> c = filtersRepo.filterAttachments(filters).getContent();
        boolean fileTest = true;
        int id = 0;
        for (FileViewDTO file : c) {
            id = file.attachmentId;
            if (id!=1001 && id!=1018){
                fileTest = false;
                break;
            }
        }
        Assertions.assertTrue(fileTest, "id doesn't match: "+ id);
    }
    @Test
    public void FiltersRepo_Auction_Types_Test(){
        List<String> types = new ArrayList<>();
        types.add("ARA3");
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .auctionTypes(types)
                .build();
        
        List<FileViewDTO> c = filtersRepo.filterAttachments(filters).getContent();
        boolean fileTest = true;
        int id = 0;
        for (FileViewDTO file : c) {
            id = file.attachmentId;
            if (id!=1052 && id!=1053){
                fileTest = false;
                break;
            }
        }
        Assertions.assertTrue(fileTest, "id doesn't match: "+ id);
    }
    @Test
    public void FiltersRepo_Auction_Period_Begin_End_Test(){
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .aucBeginDate(LocalDateTime.of(2017,6,1,0,0,0))
                .aucEndDate(LocalDateTime.of(2017,6,1,0,0,0))
                .build();
        
        FileViewDTO file = filtersRepo.filterAttachments(filters).getContent().get(0);
        Assertions.assertTrue(file.attachmentId==1053, "id doesn't match: "+file.attachmentId);
    }
    @Test
    public void FiltersRepo_Commit_Id_Test(){
        FiltersDTO filters = FiltersDTO.builder()
                .pr(defaultPageRequest)
                .sortBy(sortByCreateDate)
                .sortAsc(true)
                .commitPeriodId(1)
                .build();
        
        List<FileViewDTO> c = filtersRepo.filterAttachments(filters).getContent();
        boolean fileTest = true;
        int id = 0;
        for (FileViewDTO file : c) {
            id = file.attachmentId;
            if (id!=1001 && id!=1018){
                fileTest = false;
                break;
            }
        }
        Assertions.assertTrue(fileTest, "id doesn't match: "+ id);
    }
}
