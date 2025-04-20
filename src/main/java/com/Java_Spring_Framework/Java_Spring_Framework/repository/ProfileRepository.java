package com.Java_Spring_Framework.Java_Spring_Framework.repository;

import com.Java_Spring_Framework.Java_Spring_Framework.entity.ProfileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfileRepository extends MongoRepository<ProfileEntity, String> {
    Optional<ProfileEntity> findByUserId(String userId);
}
