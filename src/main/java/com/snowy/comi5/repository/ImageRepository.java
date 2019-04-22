package com.snowy.comi5.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.snowy.comi5.entity.ImageEntity;

@Repository
public interface ImageRepository extends PagingAndSortingRepository<ImageEntity, Long>{

}
