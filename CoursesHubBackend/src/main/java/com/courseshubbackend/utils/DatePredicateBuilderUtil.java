package com.courseshubbackend.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Map;

public class DatePredicateBuilderUtil {
    public static <T> void addDatePredicates(CriteriaBuilder builder, Root<T> root, Map<String, String> params, List<Predicate> predicates) {
        if (params.containsKey("createdMonth")) {
            int month = Integer.parseInt(params.get("createdMonth"));
            Expression<Integer> monthExpression = builder.function("month", Integer.class, root.get("createdDate"));
            predicates.add(builder.equal(monthExpression, month));
        }

        if (params.containsKey("createdYear")) {
            int year = Integer.parseInt(params.get("createdYear"));
            Expression<Integer> yearExpression = builder.function("year", Integer.class, root.get("createdDate"));
            predicates.add(builder.equal(yearExpression, year));
        }
    }
}
