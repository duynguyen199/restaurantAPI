package com.cybersoft.osahaneat.service.imp;

import com.cybersoft.osahaneat.dto.UserDTO;
import com.cybersoft.osahaneat.payload.request.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface LoginServiceImp {
    List<UserDTO> getAllUser();
    boolean checkLogin(String username,String password);

    boolean addUser(SignUpRequest signUpRequest);

}
