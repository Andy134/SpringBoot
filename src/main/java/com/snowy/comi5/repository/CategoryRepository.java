package com.snowy.comi5.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.snowy.comi5.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Long>{
	CategoryEntity findByParentId(Long id);
	CategoryEntity findByName(String name);
}
