package org.wesley.projeto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wesley.projeto.model.entities.Post;
import org.wesley.projeto.repository.PostRepository;
import org.wesley.projeto.service.exception.ObjectNotFoundException;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) throw new ObjectNotFoundException("Not found object id: " + id);
        return post.get();
    }

}
