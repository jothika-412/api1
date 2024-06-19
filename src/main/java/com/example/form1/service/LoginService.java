package com.example.form1.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.form1.common.APIResponse;
import com.example.form1.dto.LoginRequestDTO;
import com.example.form1.dto.SignUpRequestDTO;
import com.example.form1.entity.User;
import com.example.form1.repository.UserRepository;
import com.example.form1.util.JwtUtils;

@Service
public class LoginService {
	
	@Autowired
    private UserRepository userRepository;
	
	 @Autowired
	    private JwtUtils jwtUtils;

	public APIResponse signUp(SignUpRequestDTO signUpRequestDTO) {
		APIResponse apiResponse = new APIResponse();
		
		 User userEntity = new User();
		 userEntity.setFirstname(signUpRequestDTO.getFirstname());
	        userEntity.setLastname(signUpRequestDTO.getLastname());

	        userEntity.setEmailId(signUpRequestDTO.getEmailId());
	        userEntity.setPassword(signUpRequestDTO.getPassword());
	
			
	        userEntity = userRepository.save(userEntity);
		return apiResponse;
			//return null;
	
	    
}

	public APIResponse login(LoginRequestDTO loginRequestDTO) {
		
		APIResponse apiResponse = new APIResponse();
        User user = userRepository.findOneByEmailIdIgnoreCaseAndPassword(loginRequestDTO.getEmailId(), loginRequestDTO.getPassword());
        if(user == null){
        	
         apiResponse.setData("User login failed");
           return apiResponse;
        }
            String token = jwtUtils.generateJwt(user);
            
            Map<String , Object> data = new HashMap<>();
            data.put("accessToken", token);
            apiResponse.setData(data);

        
		return apiResponse;
	}
}

