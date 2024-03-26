package com.cybersoft.osahaneat.controller;

import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.payload.request.SignUpRequest;
import com.cybersoft.osahaneat.reposistory.UserReposistory;
import com.cybersoft.osahaneat.service.LoginService;
import com.cybersoft.osahaneat.service.imp.LoginServiceImp;


import com.cybersoft.osahaneat.utils.JwtUtilsHelper;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.apache.coyote.Response;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.util.Base64;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginServiceImp loginServiceImp;
    UserReposistory userReposistory;

    @Autowired
    JwtUtilsHelper jwtUtilsHelper;


    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username,@RequestParam String password){
        ResponseData responseData=new ResponseData();
        if(loginServiceImp.checkLogin(username,password)){
            String token=jwtUtilsHelper.generateToken(username);

            responseData.setData(token);
        }else {
            responseData.setData("");
            responseData.setSuccess(false);

        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);

    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest){
        ResponseData responseData=new ResponseData();

        responseData.setData(loginServiceImp.addUser(signUpRequest));

        return new ResponseEntity<>(responseData, HttpStatus.OK);

    }

    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser(){
        ResponseData responseData=new ResponseData();
        responseData.setData(loginServiceImp.getAllUser());

        return new ResponseEntity<>(responseData, HttpStatus.OK);

    }


}
