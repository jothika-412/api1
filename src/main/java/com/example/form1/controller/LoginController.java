package com.example.form1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.form1.common.APIResponse;
import com.example.form1.dto.LoginRequestDTO;
//import com.example.form1.dto.SignUpRequestDTO;
import com.example.form1.service.LoginService;
import com.example.form1.util.JwtUtils;

@Controller
public class LoginController {
	
@Autowired
private LoginService loginService;

@Autowired
private JwtUtils jwtUtils;

//@PostMapping("/signup")
//
//    public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO ){
//
//
//    APIResponse apiResponse = loginService.signUp(signUpRequestDTO);
//
//	 return ResponseEntity            
//			 .status(apiResponse.getStatus())
//             .body(apiResponse);
//
//}
@PostMapping("/login")

public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO ){


APIResponse apiResponse = loginService.login(loginRequestDTO);

 return ResponseEntity            
		 .status(apiResponse.getStatus())
         .body(apiResponse);

}
@GetMapping("/privateApi")
public ResponseEntity<APIResponse> privateApi(@RequestHeader(value = "authorization", defaultValue = "") String auth) throws Exception {
    APIResponse apiResponse =new APIResponse();

    jwtUtils.verify(auth);

    apiResponse.setData("this is private api");
    return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
}

}
