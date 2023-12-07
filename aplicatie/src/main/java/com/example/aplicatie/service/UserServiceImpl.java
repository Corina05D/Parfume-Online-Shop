package com.example.aplicatie.service;

import com.example.aplicatie.dto.RegisterDTO;
import com.example.aplicatie.dto.UserDTO;
import com.example.aplicatie.model.User;
import com.example.aplicatie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User saveUser(User user){
        Optional<User> optionalUser = userRepository.findFirstByNameAndPassword(user.getName(), user.getPassword());

        if(optionalUser.isEmpty()) {
            return userRepository.save(user);
        }
        else {
            throw new RuntimeException("User already exists!");
        }
    }


    @Override
    @Transactional
    public User saveUser2(RegisterDTO registerDTO){
        Optional<User> optionalUser = userRepository.findByNameAndEmailAndPassword(registerDTO.getName(), registerDTO.getEmail(), registerDTO.getPassword());

        if(optionalUser.isEmpty()) {
            User user = new User(registerDTO.getName(), registerDTO.getEmail(), registerDTO.getPassword());
            return userRepository.save(user);
        }
        else {
            throw new RuntimeException("User already exists!");
        }
    }


    @Override
    public List<User> readUserList(){
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public void updateUser(User user){
        userRepository.findById(user.getId()).ifPresent(user1 -> {
            user1.setName(user.getName());
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            userRepository.save(user1);
        });
    }

    @Override
    @Transactional
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findFirstByNameAndPassword(UserDTO dto){
        return userRepository.findFirstByNameAndPassword(dto.name, dto.password);
    }

    @Override
    public User findByName(String name){
        return userRepository.findFirstByName(name);
    }
}