package com.irdaislakhuafa.uploadcsv.services;

import java.util.List;

import javax.transaction.Transactional;

import com.irdaislakhuafa.uploadcsv.entities.User;
import com.irdaislakhuafa.uploadcsv.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<User> saveAll(Iterable<User> entities) {
        return userRepository.saveAll(entities);
    }
}
