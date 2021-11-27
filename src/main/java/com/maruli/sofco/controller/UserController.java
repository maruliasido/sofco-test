package com.maruli.sofco.controller;


import com.maruli.sofco.object.BasicResponse;
import com.maruli.sofco.object.RegisterUser;
import com.maruli.sofco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BasicResponse> register(@RequestBody RegisterUser registerUser) {
        try {
            userService.register(registerUser);
            return ResponseEntity.ok(new BasicResponse(true, "User has been registered"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new BasicResponse(false,e.getMessage()));
        }
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterUser registerUser){
        try {
            userService.registerAdmin(registerUser);
            return ResponseEntity.ok(new BasicResponse(true, "User has been registered"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new BasicResponse(false,e.getMessage()));
        }
    }


}