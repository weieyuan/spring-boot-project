package com.wei.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.wei.model.UserRegistry;

@Transactional
public interface UserRegistryRepository extends CrudRepository<UserRegistry, Long> {

	List<UserRegistry> findByUserName(String userName);

}
