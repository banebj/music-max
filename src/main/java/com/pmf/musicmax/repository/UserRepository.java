package com.pmf.musicmax.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pmf.musicmax.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	
}
