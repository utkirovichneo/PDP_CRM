package com.pdp.pdp_crm.filter;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@UtilityClass
public class PageableRequestUtil {

    public static Pageable toPageable(PageableRequest pageable) {
        if (Objects.nonNull(pageable.getSort())) {
            return PageRequest.of(
                    pageable.getPage(),
                    pageable.getPerPage(),
                    Sort.Direction.fromString(pageable.getSort().getDirection()),
                    pageable.getSort().getName()
            );
        }
        return PageRequest.of(
                pageable.getPage(),
                pageable.getPerPage()
        );
    }

    public static Pageable toPageable(PageableRequest pageable, Sort sort) {
        return PageRequest.of(
                pageable.getPage(),
                pageable.getPerPage(),
                sort
        );
    }
}
