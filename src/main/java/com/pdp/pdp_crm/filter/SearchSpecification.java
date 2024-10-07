package com.pdp.pdp_crm.filter;

import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@AllArgsConstructor
public class SearchSpecification<T> implements Specification<T> {

    private final List<SearchCriteria> params;

    @Override
    public Predicate toPredicate(@Nullable Root<T> root, @Nullable CriteriaQuery<?> query, @NotNull CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
