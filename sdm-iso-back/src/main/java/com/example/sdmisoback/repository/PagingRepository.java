package com.example.sdmisoback.repository;

import java.util.HashMap;
import java.util.List;

import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import com.example.sdmisoback.model.AttachProposal;
import com.example.sdmisoback.model.AttachProposal_;
import com.example.sdmisoback.model.AttachmentFile;
import com.example.sdmisoback.model.AttachmentFile_;
import com.example.sdmisoback.model.ProposalInfo;
import com.example.sdmisoback.model.ProposalInfo_;

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

    public static <T> TypedQuery<T> paginateQuery(TypedQuery<T> query, Pageable pageable) {
        if (pageable.isPaged()) {
          query.setFirstResult((int) pageable.getOffset());
          query.setMaxResults(pageable.getPageSize());
        }
        return query;
    }

    public Page<ProposalInfo> findAll(PageRequest pageRequest){
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // select * FROM ProposalInfo
        CriteriaQuery<ProposalInfo> query = cb.createQuery(ProposalInfo.class);
        Root<ProposalInfo> root = query.from(ProposalInfo.class);

        // do query: put where clauses here
        query.select(root);

        // execute and paginate query 
        List<ProposalInfo> pagedResult = paginateQuery(em.createQuery(query), pageRequest).getResultList();

        // Create a subquery to count the total number of results
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<ProposalInfo> countRoot = countQuery.from(ProposalInfo.class);

        // do query.. again but add count to it
        countQuery.select(cb.count(countRoot));
        Long count = em.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(pagedResult, pageRequest, count); 
    }

    @EntityGraph(attributePaths = {"attach_proposal"})
    public Page<AttachmentFile> findAllFilesByProposalId(PageRequest pageRequest, int id){
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // init query root AttachmentFile
        CriteriaQuery<AttachmentFile> query = cb.createQuery(AttachmentFile.class);
        Root<AttachmentFile> root = query.from(AttachmentFile.class);

        // join and filter by proposal_id
        Join<AttachmentFile, AttachProposal> attachProposalsJoin = root.join(AttachmentFile_.attachProposals);
        query
            .select(root)
            .where(cb.equal(attachProposalsJoin.get(AttachProposal_.proposalInfo).get(ProposalInfo_.proposalId), id));

        // execute and apply pagination
        List<AttachmentFile> pagedResult = paginateQuery(em.createQuery(query), pageRequest).getResultList();
        
        // count total results 
        // I cannot figure out how to just get the size of the query itself idk
        // so im just gonna call the query again idk, not the most efficient 
        int count = em.createQuery(query).getResultList().size();

        return new PageImpl<>(pagedResult, pageRequest, count);
    }
}
