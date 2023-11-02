package com.example.sdmisoback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sdmisoback.model.ProposalInfo;

@Repository
public interface ProposalRepo extends JpaRepository<ProposalInfo, Integer>, CustomProposalRepo{
}
