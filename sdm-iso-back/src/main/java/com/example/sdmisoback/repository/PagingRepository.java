package com.example.sdmisoback.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.example.sdmisoback.model.AttachProposal;
import com.example.sdmisoback.model.AttachmentFile;
import com.example.sdmisoback.model.ProposalInfo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;



@Repository
public class PagingRepository {

    @PersistenceContext
    EntityManager em;

    public Page<ProposalInfo> findAll(PageRequest pageRequest){
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // select * FROM ProposalInfo
        CriteriaQuery<ProposalInfo> cq = cb.createQuery(ProposalInfo.class);
        Root<ProposalInfo> root = cq.from(ProposalInfo.class);
        CriteriaQuery<ProposalInfo> select = cq.select(root);

        // Apply Pagination to query
        TypedQuery<ProposalInfo> typedQuery = em.createQuery(select)
                                                .setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize())
                                                .setMaxResults(pageRequest.getPageSize());

        // execute query 
        List<ProposalInfo> resultList = typedQuery.getResultList();

        // Create a subquery to count the total number of results
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(ProposalInfo.class)));
        Long count = em.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(resultList, pageRequest, count); 
    }

    public List<AttachmentFile> findAllFilesByProposalId(int id){
        // init query root AttachmentFile
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AttachmentFile> cq = cb.createQuery(AttachmentFile.class);
        Root<AttachmentFile> root = cq.from(AttachmentFile.class);

        Join<AttachmentFile, AttachProposal> attachProposalsJoin = root.join("attachProposals");
        cq.select(root);
        cq.where(cb.equal(attachProposalsJoin.get("proposalInfo").get("proposalId"), id));

        return em.createQuery(cq).getResultList();
    }
}
