package org.wesley.projeto.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wesley.projeto.model.UserDTO;
import org.wesley.projeto.model.entities.User;
import org.wesley.projeto.repository.UserRepository;
import org.wesley.projeto.service.exception.ObjectNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll() {
        List<UserDTO> list = userRepository.findAll()
                                            .stream()
                                            .map(x -> new UserDTO(x))
                                            .collect(Collectors.toList());
        return list;
    }

    public UserDTO findById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new ObjectNotFoundException("Not found object id: " + id);
        return new UserDTO(user.get());
    }

    public User findPosts(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new ObjectNotFoundException("Not found object id: " + id);
        return user.get();
    }

    public User insert(User obj) {
        obj = userRepository.save(obj);
        return obj;
    }

    public User update(User user) {
        Optional<User> obj = userRepository.findById(user.getId());
        updateData(obj.get(), user);
        return userRepository.save(obj.get());
    }

    public void updateData(User obj, User user) {
        obj.setName(user.getName());
        obj.setEmail(user.getEmail());
    }

    public void delete(String id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }

}
