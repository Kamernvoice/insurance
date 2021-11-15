package com.example.springsecurityjwt.specification;

import com.example.springsecurityjwt.entity.Offer;
import org.springframework.data.jpa.domain.Specification;

public final class OfferSpecification {
    public static Specification<Offer> termC(Integer minTerm, Integer maxTerm) {
        if(minTerm == null) minTerm = 0;
        if(maxTerm == null) maxTerm = Integer.MAX_VALUE;
        Integer finalMinTerm = minTerm;
        Integer finalMaxTerm = maxTerm;
        return ((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("term"), finalMinTerm, finalMaxTerm));
    }

    public static Specification<Offer> costC(Integer minCost, Integer maxCost) {
        if(minCost == null) minCost = 0;
        if(maxCost == null) maxCost = Integer.MAX_VALUE;
        Integer finalMinCost = minCost;
        Integer finalMaxCost = maxCost;
        return ((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("cost"), finalMinCost, finalMaxCost));
    }

    public static Specification<Offer> descriptionC(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("description"), search);
    }
}
