package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.dto.UserDTO;
import com.cybersoft.osahaneat.entity.Roles;
import com.cybersoft.osahaneat.entity.Users;
import com.cybersoft.osahaneat.payload.request.SignUpRequest;
import com.cybersoft.osahaneat.reposistory.UserReposistory;
import com.cybersoft.osahaneat.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class LoginService implements LoginServiceImp {
    @Autowired
    UserReposistory userReposistory;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public List<UserDTO> getAllUser() {
        List<Users> listUsers=  userReposistory.findAll();

        List<UserDTO> userDTOList=new ArrayList<>();

        for(Users users:listUsers){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(users.getId());
            userDTO.setUsername(users.getUsername());
            userDTO.setFullname(users.getFullname());
            userDTO.setPassword(users.getPassword());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public boolean checkLogin(String username, String password) {
       Users user= userReposistory.findByUsername(username);
       return passwordEncoder.matches(password,user.getPassword());
    }

    @Override
    public boolean addUser(SignUpRequest signUpRequest) {
        Users users=new Users();

        Roles roles=new Roles();
        roles.setId(signUpRequest.getRoleId());
        users.setFullname(signUpRequest.getFullname());
        users.setUsername((signUpRequest.getUsername()));
        users.setPassword(signUpRequest.getPassword());
        users.setRoles(roles);
        try {
            userReposistory.save(users);
            return true;
        }catch (Exception e){
            return false;
        }

    }


}
