package com.backend.blog.regository;

import com.backend.blog.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    public User findUsersByUserEmail (String userId);
}
