package com.filter.filter.controller;

import com.filter.filter.model.JwtRequest;
import com.filter.filter.model.JwtResponse;
import com.filter.filter.security.JwtHelper;
import com.filter.filter.service.impl.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserDetailsServiceImpl userDetailsServiceimpl;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;
    public AuthController(UserDetailsServiceImpl userDetailsServiceimpl, AuthenticationManager authenticationManager, JwtHelper jwtHelper) {
        this.userDetailsServiceimpl = userDetailsServiceimpl;
        this.authenticationManager = authenticationManager;
        this.jwtHelper = jwtHelper;
    }
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest){
        logger.info("inside the login API---------------> : {}",jwtRequest.getPassword());
        this.doAuthenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
        logger.info("inside the login API after doAuthenticate --------------->");
        UserDetails userDetails = userDetailsServiceimpl.loadUserByUsername(jwtRequest.getEmail());
        logger.info("user Details :{}",userDetails.getUsername());
        String token = this.jwtHelper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder().jwtToken(token)
                .userName(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    private void doAuthenticate(String email, String password) {
//        logger.info("inside the doAuthenticate email and password"+email+""+password);
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
//        logger.info("principal : {}",authentication.getPrincipal());
//        try{
//            logger.info("inside the try of doAuthenticate methods try block first statement============> ");
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
//            logger.info("inside the try of doAuthenticate methods try block ------> ");
//        }catch (BadCredentialsException e){
//            throw new BadCredentialsException("Invalid UserName or Password !!");
//        }
//    }
private void doAuthenticate(String email, String password) {
    logger.info("Inside doAuthenticate. Email: {}, Password: {}", email, password);

    try {
        // Create authentication token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // Attempt to authenticate
        logger.info("Before calling authenticate method authenticationToken : {}",authenticationToken);
        Authentication authenticated = authenticationManager.authenticate(authenticationToken);
        logger.info("After calling authenticate method authenticated : {}",authenticated);

        // Further processing with the authenticated object if needed
    } catch (BadCredentialsException e) {
        throw new BadCredentialsException("Invalid UserName or Password !!");
    }
}


    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler(){
        return "Credentials Invalid !!";
    }

}
