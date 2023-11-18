package com.example.sdmisoback.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.example.sdmisoback.model.*;

import jakarta.persistence.EntityManager;

@RestController
@RequestMapping("/api/v3/autocomplete")
public class AutoCompleteController {

    private final EntityManager em;
 
    private final CriteriaBuilderFactory cbf;
 
    public AutoCompleteController(EntityManager em, CriteriaBuilderFactory cbf) {
        this.em = em;
        this.cbf = cbf;
    }

    @GetMapping("/file-names")
    private List<String> autoCompleteFileName(@RequestParam String query){
        return cbf.create(em, String.class)
            .from(AttachmentFile.class)
            .distinct()
            .select("fileName")
            .where("fileName").like(false).value(query + "%").noEscape()
            .orderByAsc("fileName")
            .setMaxResults(10)
            .getResultList();
    }

    @GetMapping("/proposal-label")
    private List<String> autoCompleteProposalLabel(@RequestParam String query){
        return cbf.create(em, String.class)
            .from(ProposalInfo.class)
            .distinct()
            .select("proposalLabel")
            .where("proposalLabel").like(false).value("%" + query + "%").noEscape()
            .orderByAsc("proposalLabel")
            .setMaxResults(10)
            .getResultList();
    }

    @GetMapping("/project-names")
    private List<String> autoCompleteProjectName(@RequestParam String query){
        return cbf.create(em, String.class)
            .from(ProjInfo.class)
            .distinct()
            .select("projectName")
            .where("projectName").like(false).value(query + "%").noEscape()
            .orderByAsc("projectName")
            .setMaxResults(10)
            .getResultList();
    }

    @GetMapping("/customer-names")
    private List<String> autoCompleteCustomerName(@RequestParam String query){
        return cbf.create(em, String.class)
            .from(CustInfo.class)
            .distinct()
            .select("customerName")
            .where("customerName").like(false).value(query + "%").noEscape()
            .orderByAsc("customerName")
            .setMaxResults(10)
            .getResultList();
    }

    @GetMapping("/resource-names")
    private List<String> autoCompleteResourceName(@RequestParam String query){
        return cbf.create(em, String.class)
            .from(ResInfo.class)
            .distinct()
            .select("resourceName")
            .where("resourceName").like(false).value(query + "%").noEscape()
            .orderByAsc("resourceName")
            .setMaxResults(10)
            .getResultList();
    }
}
