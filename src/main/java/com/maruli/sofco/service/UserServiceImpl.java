package com.maruli.sofco.service;

import com.maruli.sofco.entity.Address;
import com.maruli.sofco.entity.User;
import com.maruli.sofco.object.RegisterUser;
import com.maruli.sofco.object.Role;
import com.maruli.sofco.repository.AddressRepository;
import com.maruli.sofco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();


    @Override
    public void register(RegisterUser registerUser) {

        User user = new User();
        Address address = new Address();
        address.setCity(registerUser.getCity());
        address.setStreet(registerUser.getStreet());
        addressRepository.save(address);

        user.setEmail(registerUser.getEmail());
        user.setFullName((registerUser.getName()));
        user.setPhoneNumber(registerUser.getPhoneNumber());
        user.setUsername(registerUser.getUsername());
        user.setRole(Role.BUYER);
        user.setPassword(bcrypt.encode(registerUser.getPassword()));
        user.setAddress(address);
        userRepository.save(user);
    }

    @Override
    public void registerAdmin(RegisterUser registerUser) {

        User user = new User();
        user.setEmail(registerUser.getEmail());
        user.setFullName((registerUser.getName()));
        user.setPhoneNumber(registerUser.getPhoneNumber());
        user.setUsername(registerUser.getUsername());
        user.setRole(Role.ADMIN);
        user.setPassword(bcrypt.encode(registerUser.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Boolean isAdmin(String username) {
        User user = userRepository.getUserByUsername(username);
        if(user != null){
            if(user.getRole().equals(Role.ADMIN)){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
