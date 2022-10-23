package com.arturces.springcourseapi.service;

import com.arturces.springcourseapi.domain.User;
import com.arturces.springcourseapi.exception.NotFoundException;
import com.arturces.springcourseapi.repository.UserRepository;
import com.arturces.springcourseapi.service.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        String hash = HashUtil.getSecureHash(user.getPassword());
        user.setPassword(hash);

        User createdUser = userRepository.save(user);
        return createdUser;
    }

    public User update(User user) {
        String hash = HashUtil.getSecureHash(user.getPassword());
        user.setPassword(hash);

        User updateUser = userRepository.save(user);
        return updateUser;
    }

    public User getById(Long id){
        Optional<User> result = userRepository.findById(id);
        return result.orElseThrow(()-> new NotFoundException("There are note user with id = " + id));
    }

    public List<User> listAll(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public User login(String email, String password){
     password = HashUtil.getSecureHash(password);

        Optional<User> result = userRepository.login(email,password);
        return result.get();
    }

}
