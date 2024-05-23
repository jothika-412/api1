package com.example.form1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.form1.common.APIResponse;
import com.example.form1.dto.LoginRequestDTO;
import com.example.form1.dto.SignUpRequestDTO;
import com.example.form1.entity.User;
import com.example.form1.repository.UserRepository;

@Service
public class LoginService {
	
	@Autowired
    private UserRepository userRepository;

	public APIResponse signUp(SignUpRequestDTO signUpRequestDTO) {
		APIResponse apiResponse = new APIResponse();
		
		 User userEntity = new User();
		 userEntity.setFirstname(signUpRequestDTO.getFirstname());
	        userEntity.setLastname(signUpRequestDTO.getLastname());

	        userEntity.setEmailId(signUpRequestDTO.getEmailId());
	        userEntity.setPassword(signUpRequestDTO.getPassword());

	        userEntity = userRepository.save(userEntity);

		
		return apiResponse;
}

	public APIResponse login(LoginRequestDTO loginRequestDTO) {
		
		APIResponse apiResponse = new APIResponse();
        User user = userRepository.findOneByEmailIdIgnoreCaseAndPassword(loginRequestDTO.getEmailId(), loginRequestDTO.getPassword());
        if(user == null){
        	
            apiResponse.setData("User login failed");
        }
        else {
            apiResponse.setData("User logged in");

        }
		return apiResponse;
	}
}

