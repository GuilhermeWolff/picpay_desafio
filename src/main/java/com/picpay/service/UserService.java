package com.picpay.service;


import com.picpay.dto.UserDTO;
import com.picpay.entity.User;
import com.picpay.entity.enums.Perfil;
import com.picpay.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserDTO userDto) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);
       try {
           if(Perfil.exists(user.getPerfil())){
               return userRepository.save(user);
           }
       } catch (Exception ex){
           log.error("Falha ao salvar novo user, erro {}", ex.getMessage());
           throw ex;
       }
       return null;
    }
    
    
    public Optional<User> findUserById(Integer id) {
    	return userRepository.findById(id);
    }


}
