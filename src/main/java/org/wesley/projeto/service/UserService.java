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

}
