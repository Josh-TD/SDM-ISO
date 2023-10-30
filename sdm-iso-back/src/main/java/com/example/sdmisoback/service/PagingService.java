package com.example.sdmisoback.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import com.example.sdmisoback.model.ProposalInfo;
import com.example.sdmisoback.repository.PagingRepository;

@Service
public class PagingService {

    private final PagingRepository repository;

    public PagingService(PagingRepository repository){
        this.repository = repository;
    }

    public Page<ProposalInfo> findAll(PageRequest pr){
        return repository.findAll(pr);
    }
}
