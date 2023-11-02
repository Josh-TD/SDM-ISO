package com.example.sdmisoback.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.sdmisoback.repository.ProposalRepo;
import com.example.sdmisoback.view.ProposalInfoSimple;


@Service
public class ProposalService {
    
    private final ProposalRepo repo;

    public ProposalService(@Qualifier("proposalRepo") ProposalRepo repo){
        this.repo = repo;
    }

    public Page<ProposalInfoSimple> findProposalSimpleById(PageRequest pr, Integer proposalId){
        return repo.findProposalSimpleById(pr, proposalId);
    }
}
