package com.jobportal.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.jobportal.entity.OTP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User,Long> {

    public Optional<User> findByEmail(String email);


}

