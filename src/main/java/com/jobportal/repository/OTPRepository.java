package com.jobportal.repository;

import com.jobportal.entity.OTP;
import com.jobportal.exception.JobPortalException;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OTPRepository extends MongoRepository<OTP,String> {

    List<OTP> findByCreationTimeBefore(LocalDateTime expirationTime);

}
