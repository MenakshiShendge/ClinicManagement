package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.AuthRequest;
import com.cg.entity.UserInfo;
import com.cg.service.JwtService;
import com.cg.service.UserInfoService;

@RestController
@RequestMapping("/user")
public class UserInfoController {
	
	@Autowired
	UserInfoService service;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
    private JwtService jwtService;
	
	@PostMapping("/new")
	public String addNewUser(@RequestBody UserInfo userInfo ) {
		return service.addUser(userInfo);
	}
	
	@PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		System.out.println(authRequest.getUsername()+" , "+authRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
 

}
}
