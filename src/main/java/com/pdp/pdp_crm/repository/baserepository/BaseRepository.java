package com.pdp.pdp_crm.repository.baserepository;

import com.pdp.pdp_crm.entity.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    Page<T> findAll(@Nullable Pageable pageable);

    @NonNull
    List<T> findAll(@Nullable Specification<T> specification);

    @NonNull
    Page<T> findAll(@Nullable Specification<T> specification, @Nullable Pageable pageable);

}