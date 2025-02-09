package org.wesley.projeto.service;

import java.util.List;
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

    public List<Post> findAll() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    public List<Post> findByTitle(String text) {
        List<Post> posts = postRepository.findByTitleContainingIgnoreCase(text);
        if (posts.isEmpty()) throw new ObjectNotFoundException("Not found object title: " + text);
        return posts;
    }

    public List<Post> searchTitle(String text) {
        List<Post> posts = postRepository.searchTitle(text);
        if (posts.isEmpty()) throw new ObjectNotFoundException("Not found object title: " + text);
        return posts;
    }

}
