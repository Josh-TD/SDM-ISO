package com.example.sdmisoback.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.sdmisoback.view.ProposalInfoSimple;

public interface CustomProposalRepo {
    Page<ProposalInfoSimple> findProposalSimpleById(PageRequest pr, Integer proposalId);
}
