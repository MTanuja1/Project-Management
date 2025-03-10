package com.tanuja.service.impl;

import com.tanuja.config.JwtProvider;
import com.tanuja.model.User;
import com.tanuja.repository.UserRepository;
import com.tanuja.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserProfileByJwt(String jwt) throws Exception {
		String email= JwtProvider.getEmailFromToken(jwt);
		return findUserByEmail(email);

	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		User user = userRepository.findByEmail(email);
		if(user == null){
			throw new Exception("user not found");
		}
		return user;
	}

	@Override
	public User findUserById(Long id) throws Exception {
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()){
			throw new Exception("user not found");
		}
		return user.get();
	}

	@Override
	public User updateUsersProjectSize(User user, int number) {
		user.setProjectSize(user.getProjectSize()+number);
		return userRepository.save(user);
	}
}
