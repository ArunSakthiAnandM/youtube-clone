package com.arun.ytclone.repository;

import com.arun.ytclone.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
//    boolean existsByUserName(String username);
//    Optional<User> findByUserName(String username);
}
