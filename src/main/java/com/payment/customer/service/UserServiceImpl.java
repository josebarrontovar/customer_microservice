package com.payment.customer.service;

import com.payment.customer.dto.UserDTO;
import com.payment.customer.entities.User;
import com.payment.customer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDto) {
        try {
            userRepository.save(modelMapper.map(userDto, User.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userDto;
    }

    @Override
    public UserDTO deleteUser(Long id) {
        UserDTO userDeleted = null;
        try {
            if (userRepository.findById(id).isPresent()) {
                userDeleted = modelMapper.map(userRepository.findById(id).get(), UserDTO.class);
                userRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userDeleted;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        try {
            List<User> listUser = userRepository.findAll();
            return listUser.stream().map(user -> modelMapper.map(user, UserDTO.class)).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDto) {
        try {
            return userRepository.findById(id).map(user -> {
                // Mapear los datos del DTO al Entity, excepto el id
                modelMapper.map(userDto, user); // Esto actualiza los campos del entity

                user.setId(id); // Aseguramos que el id se mantenga
                User updatedUser = userRepository.save(user);
                return modelMapper.map(updatedUser, UserDTO.class);
            }).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDTO findByPhoneNumber(String phoneNumber) {
        try {
            User user = userRepository.findByPhoneNumber(phoneNumber);

            return modelMapper.map(user, UserDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
