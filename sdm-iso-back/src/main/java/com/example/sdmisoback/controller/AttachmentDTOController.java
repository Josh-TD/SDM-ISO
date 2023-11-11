package com.example.sdmisoback.controller;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sdmisoback.model.FiltersDTO;
import com.example.sdmisoback.service.AttachmentService;
import com.example.sdmisoback.view.AttachmentFileView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/v3")
public class AttachmentDTOController {

    private AttachmentService service;

    public AttachmentDTOController(AttachmentService service){
        this.service = service;
    }


    // Swagger UI link for documentation and request sending 
    // http://localhost:8080/swagger-ui/index.html#/
    // Link works after the backend is launched
    // if you want to use Postman for API testing instead: use SDM email, details in the google doc
    @GetMapping("/files/list")
    @Operation(summary = "Get a single page of FileViews sorted, with many optional filters", 
               description = "returns a JPA Page object with content AttachmentFileView based on the filters and sorting applied")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved page of FileViews")
        //TODO: add error response class and respond back with proper codes 401, 400, 404
    })  
    public Page<AttachmentFileView> filterAttachments(
        // required parameters
        @RequestParam(name = "pageNum") 
        @Parameter(description = "Page number for pagination, starts at 0", example = "0") 
        int pageNum, 

        @RequestParam(name = "pageSize") 
        @Parameter(description = "Number of items to be displayed per page", example = "3") 
        int pageSize,

        // TODO: limit sortBy to set of attributes, as of now you can sort by anything
        @RequestParam(name = "sortBy") 
        @Parameter(description = "Attribute that the table should be sorted by. Valid entries are fileName, customerName, createDate, projectName", example = "createDate") 
        String sortBy,

        @RequestParam(name = "sortAsc") 
        @Parameter(description = "Which direction the table is sorted in, true=ASC false=DESC", example = "true") 
        boolean sortAsc,

        // attachmentFile filters
        @RequestParam(name = "fileId", required = false) 
        @Parameter(description = "Must be exact")
        Integer fileId,

        @RequestParam(name = "fileName", required = false) 
        @Parameter(description = "Searches for case sensitive prefix: 'Ener' can return file with name 'EnergyConverter.pdf'")
        String fileName,

        @RequestParam(name = "fileDescription", required = false) 
        @Parameter(description = "Searches for description containing input: 'word' can return file with desc 'old wordle doc'")
        String fileDescription,

        // TODO: figure out how to use this date? for now: anything greater than input date
        @RequestParam(name = "createdSince", required = false) 
        @Parameter(description = "Searches for files created after the date. Type java.time LocalDateTime, format 'yyyy-MM-ddTHH:mm:ss' ex '2018-04-01T04:00:00'")
        LocalDateTime createdSince, 

        // attachProposal filters
        // TODO: zip files can be zip or zipf or 7zip etc, make it possible for multiple parameters to be passed onto zip
        @RequestParam(name = "fileType", required = false) 
        @Parameter(description = "Searches for fileName with suffix '{fileType}'")
        String fileType,

        // proposal filters
        @RequestParam(name = "proposalId", required = false) 
        @Parameter(description = "Must be exact")
        Integer proposalId,

        @RequestParam(name = "proposalLabel", required = false) 
        @Parameter(description = "Searches for proposal label containing input: '17' can return file with proposal label '2022-05-17-FCA'")
        String proposalLabel,

        // project filters
        @RequestParam(name = "projectId", required = false) 
        @Parameter(description = "Must be exact")
        Integer projectId,

        @RequestParam(name = "projectName", required = false) 
        @Parameter(description = "Searches for case sensitive prefix: 'Ener' can return file with project name 'Energy Works'")
        String projectName,

        @RequestParam(name = "projectType", required = false) 
        @Parameter(description = "Must be exact")
        String projectType,

        // customer filters
        @RequestParam(name = "customerId", required = false) 
        @Parameter(description = "Must be exact")
        Integer customerId,

        @RequestParam(name = "customerName", required = false) 
        @Parameter(description = "Searches for case sensitive prefix: 'Ener' can return file with customer name 'Energy Importer'")
        String customerName,

        // resource filters
        @RequestParam(name = "resourceId", required = false) 
        @Parameter(description = "Must be exact")
        Integer resourceId,

        @RequestParam(name = "resourceName", required = false) 
        @Parameter(description = "Searches for case sensitive prefix: 'Ener' can return file with name 'Energy Generator'")
        String resourceName,

        @RequestParam(name = "resourceType", required = false) 
        @Parameter(description = "Must be exact")
        String resourceType,

        // auction filter (??????)
        // TODO: figure out how to use dates
        @RequestParam(name = "auctionId", required = false) 
        @Parameter(description = "Must be exact")
        Integer auctionId

        ) { //end parameters
        PageRequest pr = PageRequest.of(pageNum, pageSize);
        FiltersDTO filters = new FiltersDTO(
            pr, sortBy, sortAsc,
            fileId, fileName, fileDescription, createdSince, fileType,
            projectId, projectName, projectType,
            customerId, customerName,
            resourceId, resourceName, resourceType,
            auctionId,
            proposalId, proposalLabel
        );

        return service.filterAttachments(filters);
    }
}

