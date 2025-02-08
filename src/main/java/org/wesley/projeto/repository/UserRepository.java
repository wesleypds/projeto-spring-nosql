package org.wesley.projeto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.wesley.projeto.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {



}
