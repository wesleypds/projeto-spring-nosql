package org.wesley.projeto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.wesley.projeto.model.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {



}
