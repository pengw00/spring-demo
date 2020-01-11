package com.example.sessiondemo.service;

import com.example.sessiondemo.entity.MyUserPrincipal;
import com.example.sessiondemo.entity.User;
import com.example.sessiondemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public User getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }
    public User getUserByName(String name) {
        return userRepository.getByUsername(name);
    }


    public User create(User user){

        User user1 = userRepository.getByUsername("david");
//        user.setUserId(user1.getUserId());
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setUsername("Danno");
//        user.setEmail(user.getEmail());
        user1.setRole("ADMIN");
        return userRepository.save(user1);
    }

    @Override
    public UserDetails loadUserByUsername( String username ) {
        User user = userRepository.getByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
}
