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

    public UserEntity registration(UserEntity newUser) throws UserAlreadyExistException{
        if(userRepository.findByFullname(newUser.getFullname()) != null) {
            throw new UserAlreadyExistException("This user already exist");
        }
        else {
            userRepository.save(newUser);
            return newUser;
        }
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
}
