package com.example.sdmisoback.IntegrationTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.sdmisoback.BackendServerApp;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = BackendServerApp.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class FiltersIntegrationTest {

    @Autowired
    private MockMvc mvc;

    // TODO: change API endpoint URL when its changed
    private static String baseUrl = "/api/v3/files/list";
    private static String defaultReqParams = "?pageNum=0&pageSize=10&sortBy=createDate&sortAsc=false";
    private static String defaultRequest = baseUrl + defaultReqParams;

    @Test
    public void Filters_NoFilters_ReturnsOkAndCorrectFirstValue() throws Exception {
        mvc.perform(get(baseUrl + defaultReqParams))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042))
                .andExpect(jsonPath("$.totalElements").value(142))
                .andExpect(jsonPath("$.totalPages").value(15))
                .andExpect(jsonPath("$.size").value(10))
                .andExpect(jsonPath("$.number").value(0))
                .andExpect(jsonPath("$.first").value(true))
                .andExpect(jsonPath("$.last").value(false))
                .andExpect(jsonPath("$.empty").value(false));
    }

    @Test
    public void Filters_Error403_BadFileTypeSingular() throws Exception {
        mvc.perform(get(baseUrl + defaultReqParams + "&fileTypes=jpeg"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void Filters_Error403_BadFileTypeMultiple() throws Exception {
        mvc.perform(get(baseUrl + defaultReqParams + "&fileTypes=pdf&fileTypes=jpg&fileTypes=jpeg"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void Filters_Error403_BadSortBy() throws Exception {
        mvc.perform(get(baseUrl + "?pageNum=0&pageSize=10&sortBy=description&sortAsc=false"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void Filters_Error404_NoMatch() throws Exception {
        mvc.perform(get(baseUrl + defaultReqParams + "&fileId=999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void Filters_SingularFilter_FileId() throws Exception {
        mvc.perform(get(defaultRequest + "&fileId=1001"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1001));
    }

    @Test
    public void Filters_SingularFilter_FileIdDuplicates() throws Exception {
        mvc.perform(get(defaultRequest + "&fileId=1052"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(8))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1052));
    }

    @Test
    public void Filters_SingularFilter_FileDescription() throws Exception {
        mvc.perform(get(defaultRequest + "&fileDescription=resource"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(18))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1013));
    }

    @Test
    public void Filters_SingularFilter_FileCreateDate() throws Exception {
        mvc.perform(get(defaultRequest + "&createdSince=2015-03-08T00:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(40))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_FileTypeSingular() throws Exception {
        mvc.perform(get(defaultRequest + "&fileTypes=pdf"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(16))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1041));
    }

    @Test
    public void Filters_SingularFilter_FileTypeMultiple() throws Exception {
        mvc.perform(get(defaultRequest + "&fileTypes=pdf&fileTypes=jpg&fileTypes=docx"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(36))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1041));
    }

    @Test
    public void Filters_SingularFilter_AttachTypeSingular() throws Exception {
        mvc.perform(get(defaultRequest + "&attachTypes=PROPOSAL.OTHERS"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(22))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1038));
    }

    @Test
    public void Filters_SingularFilter_AttachTypeMultiple() throws Exception {
        mvc.perform(get(defaultRequest + "&attachTypes=PROPOSAL.OTHERS&attachTypes=QP.DR.PROJECT_DESCRIPTION"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(36))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1038));
    }

    @Test
    public void Filters_SingularFilter_ProposalId() throws Exception {
        mvc.perform(get(defaultRequest + "&proposalId=1000"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(13))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1041));
    }

    @Test
    public void Filters_SingularFilter_ProposalLabel() throws Exception {
        mvc.perform(get(defaultRequest + "&proposalLabel=2013"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(27))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1036));
    }

    @Test
    public void Filters_SingularFilter_ProposalPeriodId() throws Exception {
        mvc.perform(get(defaultRequest + "&propPeriodId=11"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(12))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_ProposalPeriodTypes() throws Exception {
        mvc.perform(get(defaultRequest + "&propPeriodTypes=COMMITMENT"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(142))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_ProposalPeriodDescription() throws Exception {
        mvc.perform(get(defaultRequest + "&propPeriodDesc=2011"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(12))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_ProposalPeriodBeginDate() throws Exception {
        mvc.perform(get(defaultRequest + "&propPeriodBeginDate=2017-06-01T00:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(76))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_ProposalPeriodEndDate() throws Exception {
        mvc.perform(get(defaultRequest + "&propPeriodEndDate=2021-06-01T00:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(88))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_ProjectId() throws Exception {
        mvc.perform(get(defaultRequest + "&projectId=5000"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(9))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1007));
    }

    @Test
    public void Filters_SingularFilter_ProjectName() throws Exception {
        mvc.perform(get(defaultRequest + "&projectName=solar"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(38))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_ProjectTypes() throws Exception {
        mvc.perform(get(defaultRequest + "&projectTypes=INCREMENTAL"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(29))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1041));
    }

    @Test
    public void Filters_SingularFilter_CustomerId() throws Exception {
        mvc.perform(get(defaultRequest + "&customerId=20"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(29))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1041));
    }

    @Test
    public void Filters_SingularFilter_CustomerName() throws Exception {
        mvc.perform(get(defaultRequest + "&customerName=cons"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(27))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_ResourceId() throws Exception {
        mvc.perform(get(defaultRequest + "&resourceId=100"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(29))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1041));
    }

    @Test
    public void Filters_SingularFilter_ResourceName() throws Exception {
        mvc.perform(get(defaultRequest + "&resourceName=solar"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(77))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_ResourceTypes() throws Exception {
        mvc.perform(get(defaultRequest + "&resourceTypes=GEN"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(67))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_AuctionId() throws Exception {
        mvc.perform(get(defaultRequest + "&auctionId=10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(10))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1053));
    }

    @Test
    public void Filters_SingularFilter_AuctionTypesSingular() throws Exception {
        mvc.perform(get(defaultRequest + "&auctionTypes=FCA"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(80))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_AuctionTypesMultiple() throws Exception {
        mvc.perform(get(defaultRequest + "&auctionTypes=FCA&auctionTypes=ANNUAL"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(93))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_AuctionBeginDate() throws Exception {
        mvc.perform(get(defaultRequest + "&aucBeginDate=2014-06-01T00:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(112))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_AuctionEndDate() throws Exception {
        mvc.perform(get(defaultRequest + "&aucEndDate=2019-06-01T00:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(69))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_CommitmentPeriodId() throws Exception {
        mvc.perform(get(defaultRequest + "&commitPeriodId=3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(45))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1041));
    }

    @Test
    public void Filters_SingularFilter_CommitmentPeriodDescription() throws Exception {
        mvc.perform(get(defaultRequest + "&commitPeriodDesc=2020"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(33))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_CommitmentPeriodTypes() throws Exception {
        mvc.perform(get(defaultRequest + "&commitPeriodTypes=COMMITMENT"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(142))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_CommitmentPeriodBeginDate() throws Exception {
        mvc.perform(get(defaultRequest + "&commitPeriodBeginDate=2012-06-01T00:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(122))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_CommitmentPeriodEndDate() throws Exception {
        mvc.perform(get(defaultRequest + "&commitPeriodEndDate=2019-06-01T00:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(109))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_AuctionPeriodId() throws Exception {
        mvc.perform(get(defaultRequest + "&aucPeriodId=6"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(11))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_AuctionPeriodDescription() throws Exception {
        mvc.perform(get(defaultRequest + "&aucPeriodDesc=201"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(69))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_AuctionPeriodTypes() throws Exception {
        mvc.perform(get(defaultRequest + "&aucPeriodTypes=COMMITMENT"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(142))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_AuctionPeriodBeginDate() throws Exception {
        mvc.perform(get(defaultRequest + "&aucPeriodBeginDate=2012-06-01T00:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(140))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1042));
    }

    @Test
    public void Filters_SingularFilter_AuctionPeriodEndDate() throws Exception {
        mvc.perform(get(defaultRequest + "&aucPeriodEndDate=2014-06-01T00:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(25))
                .andExpect(jsonPath("$.content[0].attachmentId").value(1038));
    }

    // TODO: For all the "Types" Filters, make call for multiple
    // TODO: All more tests where there is more than 1 filter being applied
    // TODO: SQL injection test (kinda redundent since we already know its SQL injection proof)
}
