package com.example.sdmisoback.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sdmisoback.model.ProposalInfo;
import com.example.sdmisoback.service.PagingService;

@RestController
@RequestMapping("/api")
public class PagingController {
    
    private PagingService service;

    public PagingController(PagingService service){
        this.service = service;
    }

    @GetMapping("/paged-proposals")
    public Page<ProposalInfo> findAll(@RequestParam int page, @RequestParam int size){
        PageRequest pr = PageRequest.of(page, size);
        return service.findAll(pr);
    }
}
