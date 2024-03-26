package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.dto.UserDTO;
import com.cybersoft.osahaneat.entity.Users;
import com.cybersoft.osahaneat.reposistory.UserReposistory;
import com.cybersoft.osahaneat.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceService implements UserServiceImp {
    @Autowired
    UserReposistory userReposistory;
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
}
