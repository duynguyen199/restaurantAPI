package com.cybersoft.osahaneat.security;

import com.cybersoft.osahaneat.entity.Users;
import com.cybersoft.osahaneat.reposistory.UserReposistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CustomerUserDetailService implements UserDetailsService {
    @Autowired
    UserReposistory userReposistory;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Users users= userReposistory.findByUsername(username);
        if(users == null){
            throw new UsernameNotFoundException("User does not exist");
        }
        return new User(username,users.getPassword(), new ArrayList<>());
    }
}
