package com.blog.backendapi.services.implemention;

import java.util.List;
import java.util.stream.Collectors;

import com.blog.backendapi.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.backendapi.model.User;
import com.blog.backendapi.payloads.UserDto;
import com.blog.backendapi.repo.UserRepo;
import com.blog.backendapi.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		
		return this.usertoDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		UserDto  userDto1 = this.usertoDto(updatedUser);
		
		return userDto1;
	}

	@Override
	public UserDto getUserByid(Integer userId) {


		User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));
		
		return this.usertoDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		
		List<UserDto> usersDtos = users.stream().map(user -> this.usertoDto(user)).collect(Collectors.toList());
		return null;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", " id ", userId));
		
		this.userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto) {
		
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		return user;
	}
	
	private UserDto usertoDto(User user) {
		
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setAbout(user.getAbout());
		userDto.setPassword(user.getPassword());
		return userDto;
	}

}
