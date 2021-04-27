package com.info.test.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.info.test.springboot.model.UserTbl;
@Repository
public interface UserRepository extends CrudRepository<UserTbl, Integer> {

	public UserTbl findByGmail(String gmail);
}
