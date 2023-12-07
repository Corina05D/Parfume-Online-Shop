package com.example.aplicatie.repository;

import com.example.aplicatie.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository <User, Long>{
    User findFirstByName(String name);
    Optional<User> findFirstByNameAndPassword(String name, String password);
    Optional<User> findByNameAndEmailAndPassword(String name, String email, String password);
}
