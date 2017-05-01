package com.wei.repository;

import org.springframework.data.repository.CrudRepository;

import com.wei.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
