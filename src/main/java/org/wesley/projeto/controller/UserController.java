package org.wesley.projeto.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.wesley.projeto.model.UserDTO;
import org.wesley.projeto.model.entities.Post;
import org.wesley.projeto.model.entities.User;
import org.wesley.projeto.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User user = userService.findPosts(id);
        return ResponseEntity.ok().body(user.getPosts());
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO) {
        User user = userService.fromDTO(userDTO);
        user = userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                                                .path("/{id}")
                                                .buildAndExpand(user.getId())
                                                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO, @PathVariable String id) {
        User user = userService.fromDTO(userDTO);
        user.setId(id);
        user = userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
