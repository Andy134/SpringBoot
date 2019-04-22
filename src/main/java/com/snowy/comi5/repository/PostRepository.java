package com.snowy.comi5.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.snowy.comi5.entity.PostEntity;

@Repository
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Long>{
	PostEntity findByTitle(String title);
}
