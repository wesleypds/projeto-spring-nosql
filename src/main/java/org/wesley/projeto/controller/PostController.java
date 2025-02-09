package org.wesley.projeto.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wesley.projeto.controller.util.URL;
import org.wesley.projeto.model.entities.Post;
import org.wesley.projeto.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = postService.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        List<Post> posts = postService.findAll();
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> posts = postService.searchTitle(text);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/searchcriteria")
    public ResponseEntity<List<Post>> searchCriteria(
        @RequestParam(value = "text", defaultValue = "") String text,
        @RequestParam(value = "dateMin", defaultValue = "") String dateMin,
        @RequestParam(value = "dateMax", defaultValue = "") String dateMax
        ) {
        text = URL.decodeParam(text);
        Date min = URL.convertDate(dateMin, new Date(0L));
        Date max = URL.convertDate(dateMax, new Date());
        List<Post> posts = postService.searchCriteria(text, min, max);
        return ResponseEntity.ok().body(posts);
    }

}
