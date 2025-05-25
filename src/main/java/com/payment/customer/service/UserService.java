package com.payment.customer.service;

import com.payment.customer.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user);

    UserDTO deleteUser(Long id);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(Long id, UserDTO userDto);

    UserDTO findByPhoneNumber(String phoneNumber);

}
