package com.example.sdmisoback.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.sdmisoback.service.ProposalService;
import com.example.sdmisoback.view.ProposalInfoSimple;


@RestController
@RequestMapping("/api/v3")
public class ProposalController {

    private ProposalService service;

    public ProposalController(ProposalService service){
        this.service = service;
    }

    // api endpoint at http://localhost:8080/api/v3/paged-proposals?pageNum={int}&pageSize={int}&proposalId={int}
    // recommended pageNum=3&pageSize=3  proposalId optional but recommended = 333
    @GetMapping("/paged-proposals")
    public Page<ProposalInfoSimple> findProposalSimpleById(
        @RequestParam(name = "pageNum") int page, 
        @RequestParam(name = "pageSize") int size, 
        @RequestParam(name = "proposalId", required = false) Integer id) {
        
        PageRequest pr = PageRequest.of(page, size);
        return service.findProposalSimpleById(pr, id);
    }
}

