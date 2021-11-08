package com.example.demo.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	private static final Logger l = LogManager.getLogger(UserServiceImpl.class);
	
	@Override
	public List<User> retrieveAllUsers() { 
		List<User> users = null; 
		try {
	
			l.info("in retrieve all users");
			users = (List<User>) userRepository.findAll();  
			for (User user : users) {
				l.debug("user" + user.getLastName());
			} 
			l.info("out retrieve all users");
		}catch (Exception e) {
			l.error("out of method retrieve alla users with error : " + e);
			
		}

		return users;
	}


	@Override
	public User addUser(User u) {
		l.info("in add user");
		User u_saved = userRepository.save(u); 
		l.info("user added no errors");
		return u_saved; 
	}

	@Override 
	public User updateUser(User u) { 
		
		User u_saved = userRepository.save(u); 
		l.info("user updated");
		
		return u_saved; 
	}

	@Override
	public void deleteUser(String id) {
		l.info("in delete user");
		userRepository.deleteById(Long.parseLong(id)); 
		l.info("user deleted") ;
	}

	@Override
	public User retrieveUser(String id) {
		l.info("retrieve user in");
		//User u =  userRepository.findById(Long.parseLong(id)).orElse(null);
		User u =  userRepository.findById(Long.parseLong(id)).get(); 
		l.info("retrieve out");
		return u; 
	}

}