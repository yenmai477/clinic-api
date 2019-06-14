package com.yenmai.clinicrestfulapi.rest;


import com.yenmai.clinicrestfulapi.auth.JwtProvider;
import com.yenmai.clinicrestfulapi.dao.NhanVienRepository;
import com.yenmai.clinicrestfulapi.dao.TaiKhoanRespository;
import com.yenmai.clinicrestfulapi.entity.TaiKhoan;
import com.yenmai.clinicrestfulapi.model.JwtResponse;
import com.yenmai.clinicrestfulapi.model.LoginForm;
import com.yenmai.clinicrestfulapi.model.ResponseMessage;
import com.yenmai.clinicrestfulapi.model.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author YenMai
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TaiKhoanRespository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    NhanVienRepository nhanVienRepository;



    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        String  userRole = userRepository.findById(loginRequest.getUsername()).get().getQuyen();

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt, userRole));
    }



    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (userRepository.existsById(signUpRequest.getTenTaiKhoan())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }


        // Creating user's account
        TaiKhoan user = new TaiKhoan(signUpRequest.getTenTaiKhoan()
                ,encoder.encode(signUpRequest.getMatKhau())
                ,signUpRequest.getQuyen()
        );


        user.setNhanVien(nhanVienRepository.findById(signUpRequest.getMaNhanVien()).get());


        userRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

}
