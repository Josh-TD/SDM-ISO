package com.example.sdmisoback.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.sdmisoback.dto.FileViewDTO;
import com.example.sdmisoback.dto.FiltersDTO;
import com.example.sdmisoback.repository.FiltersRepo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v3")
@CrossOrigin("http://localhost:3000")
public class FiltersController {

    private final FiltersRepo filtersRepo;

    // Swagger UI link for documentation and request sending 
    // http://localhost:8080/swagger-ui/index.html#/
    // Link works after the backend is launched
    // if you want to use Postman for API testing instead: use SDM email, details in the google doc
    @GetMapping("/files/list")
    @Operation(summary = "Get a single page of FileViews sorted, with many optional filters", 
               description = "returns a Spring Page object with content FileViewDTO based on the filters and sorting applied")
    @ApiResponses(value = {

        @ApiResponse(responseCode = "200", description = "Successfully retrieved page of FileViews",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class),
            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(name = "Single File As Content",
            value = "{\"content\": [{\"attachmentId\": 1, \"fileName\": \"example.txt\", \"fileDescription\": \"Sample File\", \"fileCreateDate\": \"2023-01-01T12:00:00\", \"projectId\": 1, \"projectName\": \"Project A\", \"customerId\": 1, \"customerName\": \"Customer X\"}], \"totalElements\": 1, \"totalPages\": 1, \"size\": 10, \"number\": 0, \"first\": true, \"last\": true, \"empty\": false}"))),
        @ApiResponse(responseCode = "400", description = "Invalid filter parameters",
            content = @Content(mediaType = "text/plain")),
        @ApiResponse(responseCode = "404", description = "Query Successful, but no files matching the filter criteria were found",
            content = @Content(mediaType = "text/plain"))
    })  
    public ResponseEntity<?> filterAttachments(
        // required parameters
        @RequestParam(name = "pageNum") 
        @Parameter(description = "Page number for pagination, starts at 0", example = "0") 
        int pageNum, 

        @RequestParam(name = "pageSize") 
        @Parameter(description = "Number of items to be displayed per page", example = "10") 
        int pageSize,

        @RequestParam(name = "sortBy") 
        @Parameter(description = "Attribute that the table should be sorted by. Valid entries are fileName, customerName, createDate, projectName", example = "createDate") 
        String sortBy,

        @RequestParam(name = "sortAsc") 
        @Parameter(description = "Which direction the table is sorted in, true=ASC false=DESC", example = "false") 
        boolean sortAsc,

        // attachmentFile filters
        @RequestParam(name = "fileId", required = false) 
        @Parameter(description = "Must be exact")
        Integer fileId,

        @RequestParam(name = "fileName", required = false) 
        @Parameter(description = "Searches for prefix: 'ener' can return file with name 'EnergyConverter.pdf'")
        String fileName,

        @RequestParam(name = "fileDescription", required = false) 
        @Parameter(description = "Searches for description containing input: 'word' can return file with desc 'old wordle doc'")
        String fileDescription,

        @RequestParam(name = "createdSince", required = false) 
        @Parameter(description = "Searches for files created after the date. Type java.time LocalDateTime, format 'yyyy-MM-ddTHH:mm:ss' ex '2018-04-01T04:00:00'")
        LocalDateTime createdSince, 

        @RequestParam(name = "fileTypes", required = false) 
        @Parameter(description = "Searches for fileNames with any suffix in {fileTypes}. Swagger: each line is 'pdf' 'docx'. URL: 'pdf, docx'")
        List<String> fileTypes,

        // attachProposal filters
        @RequestParam(name = "attachTypes", required = false) 
        @Parameter(description = "Must be exact, searches for any match for {attachTypes}")
        List<String> attachTypes,

        // proposal filters
        @RequestParam(name = "proposalId", required = false) 
        @Parameter(description = "Must be exact")
        Integer proposalId,

        @RequestParam(name = "proposalLabel", required = false) 
        @Parameter(description = "Searches for proposal label containing input: '17' can return file with proposal label '2022-05-17-FCA'")
        String proposalLabel,

        @RequestParam(name = "propPeriodId", required = false) 
        @Parameter(description = "Must be exact")
        Integer propPeriodId,

        @RequestParam(name = "propPeriodTypes", required = false) 
        @Parameter(description = "Must be exact, can have multiple")
        List<String> propPeriodTypes,

        @RequestParam(name = "propPeriodDesc", required = false) 
        @Parameter(description = "Searches for prefix: '200' can return file with proposal description '2001-02'")
        String propPeriodDesc,

        @RequestParam(name = "propPeriodBeginDate", required = false) 
        @Parameter(description = "Searches for begin date after {propPeriodBeginDate} format 'yyyy-MM-ddTHH:mm:ss' ex '2018-04-01T00:00:00'. Timestamp does matter, use with time 00:00:00 or some standard")
        LocalDateTime propPeriodBeginDate,

        @RequestParam(name = "propPeriodEndDate", required = false) 
        @Parameter(description = "Searches for end date before {propPeriodEndDate} format 'yyyy-MM-ddTHH:mm:ss' ex '2018-04-01T00:00:00'. Timestamp does matter, use with time 00:00:00 or some standard")
        LocalDateTime propPeriodEndDate,

        // project filters
        @RequestParam(name = "projectId", required = false) 
        @Parameter(description = "Must be exact")
        Integer projectId,

        @RequestParam(name = "projectName", required = false) 
        @Parameter(description = "Searches for prefix: 'ener' can return file with project name 'Energy Works'")
        String projectName,

        @RequestParam(name = "projectTypes", required = false) 
        @Parameter(description = "Must be exact, searches for any match for {projectTypes}")
        List<String> projectTypes,

        // customer filters
        @RequestParam(name = "customerId", required = false) 
        @Parameter(description = "Must be exact")
        Integer customerId,

        @RequestParam(name = "customerName", required = false) 
        @Parameter(description = "Searches for prefix: 'ener' can return file with customer name 'Energy Importer'")
        String customerName,

        // resource filters
        @RequestParam(name = "resourceId", required = false) 
        @Parameter(description = "Must be exact")
        Integer resourceId,

        @RequestParam(name = "resourceName", required = false) 
        @Parameter(description = "Searches for prefix: 'ener' can return file with name 'Energy Generator'")
        String resourceName,

        @RequestParam(name = "resourceTypes", required = false) 
        @Parameter(description = "Must be exact, searches for any match for {resourceTypes}")
        List<String> resourceTypes,

        // auction filters
        @RequestParam(name = "auctionId", required = false) 
        @Parameter(description = "Must be exact")
        Integer auctionId,

        @RequestParam(name = "auctionTypes", required = false) 
        @Parameter(description = "Must be exact, searches for any match for {auctionTypes}")
        List<String> auctionTypes,

        @RequestParam(name = "aucBeginDate", required = false) 
        @Parameter(description = "Searches for begin date before {aucBeginDate} format 'yyyy-MM-ddTHH:mm:ss' ex '2018-04-01T00:00:00'. Timestamp does matter, use with time 00:00:00 or some standard")
        LocalDateTime aucBeginDate,

        @RequestParam(name = "aucEndDate", required = false) 
        @Parameter(description = "Searches for end date before {aucEndDate} format 'yyyy-MM-ddTHH:mm:ss' ex '2018-04-01T00:00:00'. Timestamp does matter, use with time 00:00:00 or some standard")
        LocalDateTime aucEndDate,

        // commitment period filters
        @RequestParam(name = "commitPeriodId", required = false) 
        @Parameter(description = "Must be exact")
        Integer commitPeriodId,

        @RequestParam(name = "commitPeriodTypes", required = false) 
        @Parameter(description = "Must be exact, can input multiple")
        List<String> commitPeriodTypes,

        @RequestParam(name = "commitPeriodDesc", required = false) 
        @Parameter(description = "Searches for prefix: '200' can return file with commit period description '2001-02'")
        String commitPeriodDesc,

        @RequestParam(name = "commitPeriodBeginDate", required = false) 
        @Parameter(description = "Searches for begin date before {commitPeriodBeginDate} format 'yyyy-MM-ddTHH:mm:ss' ex '2018-04-01T00:00:00'. Timestamp does matter, use with time 00:00:00 or some standard")
        LocalDateTime commitPeriodBeginDate,

        @RequestParam(name = "commitPeriodEndDate", required = false) 
        @Parameter(description = "Searches for end date before {commitPeriodEndDate} format 'yyyy-MM-ddTHH:mm:ss' ex '2018-04-01T00:00:00'. Timestamp does matter, use with time 00:00:00 or some standard")
        LocalDateTime commitPeriodEndDate,
    
        @RequestParam(name = "aucPeriodId", required = false) 
        @Parameter(description = "Must be exact")
        Integer aucPeriodId,

        @RequestParam(name = "aucPeriodTypes", required = false) 
        @Parameter(description = "Must be exact, can input multiple")
        List<String> aucPeriodTypes,

        @RequestParam(name = "aucPeriodDesc", required = false) 
        @Parameter(description = "Searches for prefix: '200' can return file with auction period description '2001-02'")
        String aucPeriodDesc,

        @RequestParam(name = "aucPeriodBeginDate", required = false) 
        @Parameter(description = "Searches for begin date before {aucPeriodBeginDate} format 'yyyy-MM-ddTHH:mm:ss' ex '2018-04-01T00:00:00'. Timestamp does matter, use with time 00:00:00 or some standard")
        LocalDateTime aucPeriodBeginDate,

        @RequestParam(name = "aucPeriodEndDate", required = false) 
        @Parameter(description = "Searches for end date before {aucPeriodEndDate} format 'yyyy-MM-ddTHH:mm:ss' ex '2018-04-01T00:00:00'. Timestamp does matter, use with time 00:00:00 or some standard")
        LocalDateTime aucPeriodEndDate

        ) { //end parameters

        List<String> validSortBy = Arrays.asList(
            "fileName", "createDate", "customerName", "projectName"
        );

        List<String> validFileTypes = Arrays.asList(
            "bmp", "jpg",
            "doc", "docx",
            "htm", "html",
            "msg",
            "pdf",
            "txt",
            "xlsm", "xlsx",
            "zip", "zipx"
        );
        
        // process parameters
        PageRequest pr = PageRequest.of(pageNum, pageSize);
        if (!validSortBy.contains(sortBy))
            return ResponseEntity.badRequest().body("Invalid sortBy value: " + sortBy);
        
        switch(sortBy){
            case "customerName":
                sortBy = "c.customerName";
                break;
            case "projectName":
                sortBy = "pj.projectName";
                break;
            case "createDate":
                sortBy = "af.createDate";
                break;
            case "fileName":
                sortBy = "af.fileName";
                break;
        }
        
        if (fileTypes != null && !fileTypes.stream().allMatch(validFileTypes::contains))
            return ResponseEntity.badRequest().body("Invalid fileType value: " + fileTypes);

        FiltersDTO filters = new FiltersDTO(
            pr, sortBy, sortAsc,
            fileId, fileName, fileDescription, createdSince, fileTypes,
            attachTypes,
            proposalId, proposalLabel, propPeriodId, propPeriodTypes, propPeriodDesc, propPeriodBeginDate, propPeriodEndDate,
            projectId, projectName, projectTypes,
            customerId, customerName,
            resourceId, resourceName, resourceTypes,
            auctionId, auctionTypes, aucBeginDate, aucEndDate,
            commitPeriodId, commitPeriodTypes, commitPeriodDesc, commitPeriodBeginDate, commitPeriodEndDate,
            aucPeriodId, aucPeriodTypes, aucPeriodDesc, aucPeriodBeginDate, aucPeriodEndDate
        );

        Page<FileViewDTO> result = filtersRepo.filterAttachments(filters);
        if(result.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Query Successful, but no files matching the filter criteria were found");
        
        return ResponseEntity.ok(result);
    }
}
