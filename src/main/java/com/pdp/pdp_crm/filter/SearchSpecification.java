package com.pdp.pdp_crm.filter;

import com.google.common.base.CaseFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class SearchSpecification<T> implements Specification<T> {

    private final List<SearchCriteria> params;

    @Override
    public Predicate toPredicate(@Nullable Root<T> root, @Nullable CriteriaQuery<?> query, @NotNull CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();
        if(params != null){
            SearchCriteria isGlobalFound =null;
            boolean isGlobalItems = false;
            List<Predicate> globalItems = new ArrayList<>();
            List<Predicate> nonGlobalItems = new ArrayList<>();
            List<Predicate> predicates = new ArrayList<>();

            for (SearchCriteria param : params) {
                String[] keys = param.getKey().split("\\.");

                    if (param.getKey().split("_").length > 1)
                        param.setKey(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, param.getKey()));

                    if(keys.length > 1){
                        Join<?, ?> join = root.join(keys[0]);
                        for (String key : keys) {
                            join = join.join(key);
                        }
                        param.setKey(keys[keys.length - 1]);
                        predicates.add(getPredicate(param, criteriaBuilder, join));
                    }
                    else{
                        predicates.add(getPredicate(param, criteriaBuilder, root));
                    }

                    if (param.getIsGlobal()){
                        isGlobalItems = true;
                        globalItems.add(predicates.get(predicates.size() - 1));
                    }
                    else{
                        nonGlobalItems.add(predicates.get(predicates.size() - 1));
                    }

                if (isGlobalFound == null &&
                        (param.getKey().equals("status") || param.getKey().equals("entityStatus"))
                ) {
                    isGlobalFound = param;
                }
            }
                Predicate predicate1 = null;
                Predicate predicateGlobal = null;
                Predicate predicateNonGlobal = null;

                if(isGlobalItems){
                    predicateGlobal = criteriaBuilder.or(globalItems.toArray(Predicate[]::new));
                    predicateNonGlobal = criteriaBuilder.and(nonGlobalItems.toArray(Predicate[]::new));
                    return criteriaBuilder.and(predicateGlobal, predicateNonGlobal);
                }else{
                    predicate1 = criteriaBuilder.and(predicates.toArray(Predicate[]::new));
                }

                if(isGlobalFound != null){
                    Predicate predicateParam = null;
                    predicateParam = getPredicate(isGlobalFound, criteriaBuilder, root);
                    return criteriaBuilder.and(predicate1, predicateParam);
                }
                return predicate1;
        }
        return predicate;
    }

    @SuppressWarnings("all")
    private Predicate getPredicate(SearchCriteria param, CriteriaBuilder builder, Root<T> root) {
        return switch (param.getOperation()){
            case ">" -> builder.greaterThan(root.get(param.getKey()), param.getValue().toString());
            case "<" -> builder.lessThan(root.get(param.getKey()), param.getValue().toString());
            case ">=" -> builder.greaterThanOrEqualTo(root.get(param.getKey()), param.getValue().toString());
            case "<=" -> builder.lessThanOrEqualTo(root.get(param.getKey()), param.getValue().toString());
            case "%_" -> builder.like(root.get(param.getKey()), "%" + param.getValue());
            case "_%" -> builder.like(root.get(param.getKey()), param.getValue() + "%");
            case "%_%" -> builder.like(root.get(param.getKey()), "%" + param.getValue() + "%");
            case "!=" -> getPredicateNotEqual(param, builder, root);
            default -> getPredicateOnEquel(param, builder, root);
        };
    }

    @SuppressWarnings("all")
    private Predicate getPredicate(SearchCriteria param, CriteriaBuilder builder, Join<?, ?> join) {
        return switch (param.getOperation()){
            case ">" -> builder.greaterThan(join.get(param.getKey()), param.getValue().toString());
            case "<" -> builder.lessThan(join.get(param.getKey()), param.getValue().toString());
            case ">=" -> builder.greaterThanOrEqualTo(join.get(param.getKey()), param.getValue().toString());
            case "<=" -> builder.lessThanOrEqualTo(join.get(param.getKey()), param.getValue().toString());
            case "%_" -> builder.like(join.get(param.getKey()), "%" + param.getValue());
            case "_%" -> builder.like(join.get(param.getKey()), param.getValue() + "%");
            case "%_%" -> builder.like(join.get(param.getKey()), "%" + param.getValue() + "%");
            case "!=" -> getPredicateNotEqual(param, builder, join);
            default -> getPredicateOnEquel(param, builder, join);
        };
    }

    @SuppressWarnings("all")
    private Predicate getPredicateNotEqual(SearchCriteria param, CriteriaBuilder builder, Join<?, ?> join) {
        String[] keys = param.getKey().split("\\.");
        Class<?> paramType = join.get(keys[0]).getJavaType();
        String value = param.getValue() == null ? null : (String) param.getValue().toString();
        if (keys.length == 2) {
            return builder.notEqual(builder.function(
                    "jsonb_extract_path_text",
                    String.class,
                    join.get(keys[0]), builder.literal(keys[1])), value);
        } else if (value == null)
            return builder.isNotNull(join.get(keys[0]));
        if (paramType.equals(String.class))
            return builder.notEqual(join.get(param.getKey()), value);
        else if (paramType.equals(UUID.class))
            return builder.notEqual(join.get(param.getKey()), UUID.fromString(value));
        else if (paramType.isEnum())
            return builder.notEqual(join.get(param.getKey()), Enum.valueOf((Class<Enum>) paramType, value));
        else
            return builder.notEqual(join.get(param.getKey()), param.getValue());

    }

    @SuppressWarnings("all")
    private Predicate getPredicateNotEqual(SearchCriteria param, CriteriaBuilder builder, Root<T> root) {
        String[] keys = param.getKey().split("\\.");
        Class<?> paramType = root.get(keys[0]).getJavaType();
        String value = param.getValue() == null ? null : (String) param.getValue().toString();
        if (keys.length == 2) {
            return builder.notEqual(builder.function(
                    "jsonb_extract_path_text",
                    String.class,
                    root.get(keys[0]), builder.literal(keys[1])), value);
        } else if (value == null)
            return builder.isNotNull(root.get(keys[0]));
        if (paramType.equals(String.class))
            return builder.notEqual(root.get(param.getKey()), value);
        else if (paramType.equals(UUID.class))
            return builder.notEqual(root.get(param.getKey()), UUID.fromString(value));
        else if (paramType.isEnum())
            return builder.notEqual(root.get(param.getKey()), Enum.valueOf((Class<Enum>) paramType, value));
        else
            return builder.notEqual(root.get(param.getKey()), param.getValue());
    }


    private Predicate getPredicateOnEquel(SearchCriteria param, CriteriaBuilder builder, Root<T> root) {
        String[] keys = param.getKey().split("\\.");
        Class<?> paramType = root.get(keys[0]).getJavaType();
        String value = param.getValue() == null ? null : param.getValue().toString();
            if (keys.length >= 2)
                return builder.equal(builder.function(
                        "jsonb_extract_path_text",
                String.class,
                root.get(keys[0]),builder.literal(keys[1])),value);

            else if(value == null)
                return builder.isNull(root.get(keys[0]));

            else if(paramType.equals(String.class))
                return builder.equal(root.get(keys[0]), value);

            else if(paramType.equals(UUID.class))
                return builder.equal(root.get(param.getKey()), UUID.fromString(value));

            else if(Enum.class.isAssignableFrom(paramType))
                return builder.equal(root.get(param.getKey()), Enum.valueOf((Class<Enum>) paramType, value));

            else if(List.class.isAssignableFrom(paramType))
                return builder.isMember(param.getValue(), root.get(param.getKey()));

            else return builder.equal(root.get(param.getKey()), param.getValue());
    }

    private Predicate getPredicateOnEquel(SearchCriteria param, CriteriaBuilder builder, Join<?, ?> join) {
        String[] keys = param.getKey().split("\\.");
        Class<?> paramType = join.get(keys[0]).getJavaType();
        String value = param.getValue() == null ? null : param.getValue().toString();

        if(value == null)
            return builder.isNull(join.get(keys[0]));

        else if(paramType.equals(String.class))
            return builder.equal(join.get(keys[0]), value);

        else if(paramType.equals(UUID.class))
            return builder.equal(join.get(param.getKey()), UUID.fromString(value));

        else if(Enum.class.isAssignableFrom(paramType))
            return builder.equal(join.get(param.getKey()), Enum.valueOf((Class<Enum>) paramType, value));

        else if(List.class.isAssignableFrom(paramType))
            return builder.isMember(param.getValue(), join.get(param.getKey()));

        else return builder.equal(join.get(param.getKey()), param.getValue());
    }
}
