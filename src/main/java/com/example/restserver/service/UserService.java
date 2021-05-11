package com.example.restserver.service;


import com.example.restserver.entity.UserEntity;
import com.example.restserver.exception.UserAlreadyExistException;
import com.example.restserver.exception.UserNotFoundException;
import com.example.restserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity save(UserEntity newUser){
        userRepository.save(newUser);
        return newUser;
    }

    public UserEntity findUserById(Long id) throws UserNotFoundException {
        if(userRepository.existsById(id)) {
            return userRepository.findById(id).orElse(new UserEntity());
        }
        else
            throw new UserNotFoundException("User Not Found");
    }

    public boolean deleteUserById(Long id) throws UserNotFoundException {
        if(userRepository.existsById(id)) {
            userRepository.delete(findUserById(id));
            return true;
        }
        else
            throw new UserNotFoundException("User Not Found");
    }

    public UserEntity findByEmailAndPassword(String email, String password) throws UserNotFoundException{
        UserEntity user  = userRepository.findByEmailAndPassword(email, password);
        if(user == null) throw new UserNotFoundException("Пользователь с таким логином и паролем не найлен");
        return user;
    }

    public boolean userExist(UserEntity user) {
        if(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()) != null) {
            return true;
        }
        return false;
    }

    public boolean existByEmail(String email) {
        if(userRepository.findByEmail(email) != null) {
            return true;
        }
        return false;
    }

    public UserEntity findByEmail(String email) throws UserNotFoundException{
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("Пользователь с такой почтой не найден");
        }
        return user;
    }
}
