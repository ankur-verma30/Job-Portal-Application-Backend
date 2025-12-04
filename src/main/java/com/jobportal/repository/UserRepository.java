package com.jobportal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    
}
