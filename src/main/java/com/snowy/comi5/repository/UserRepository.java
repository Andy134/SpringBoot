package com.snowy.comi5.repository;

import org.springframework.stereotype.Repository;

import com.snowy.comi5.entity.UserEntity;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{
	UserEntity findByEmail(String email) ;
	UserEntity findByUserId(String userId);
}
