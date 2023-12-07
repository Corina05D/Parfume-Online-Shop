package com.example.aplicatie.service;

import com.example.aplicatie.dto.RegisterDTO;
import com.example.aplicatie.dto.UserDTO;
import com.example.aplicatie.model.User;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public interface UserService {

    User saveUser(User user);
    User saveUser2(RegisterDTO registerDTO);
    List<User> readUserList();
    @Transactional
    void updateUser(User user);
    @Transactional
    void deleteUser(Long id);
    User findByName(String name);
    Optional<User> findFirstByNameAndPassword(UserDTO dto);
}
