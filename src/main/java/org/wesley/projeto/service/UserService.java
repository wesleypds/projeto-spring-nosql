package org.wesley.projeto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wesley.projeto.model.UserDTO;
import org.wesley.projeto.repository.UserRepository;

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

}
