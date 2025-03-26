package com.Java_Spring_Framework.Java_Spring_Framework.repository;

import com.Java_Spring_Framework.Java_Spring_Framework.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRepository extends MongoRepository<UserEntity,String> {
    Optional<UserEntity> findByUsername(String username);
}
