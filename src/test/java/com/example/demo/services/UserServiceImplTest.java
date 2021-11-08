package com.example.demo.services;



import java.text.ParseException; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.IUserService;
import com.example.demo.services.UserServiceImpl;

@SpringBootTest
public class UserServiceImplTest {
	
	@Autowired
	IUserService us = null;
	
	private static final Logger l = LogManager.getLogger(UserServiceImpl.class);
	
		@Test
		@Order(1)
		public void testAddUser() throws ParseException {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date d = dateFormat.parse("1995-11-08");
			User u = new User("zakaria" , "Faltani " , d , Role.CHEF_DEPARTEMENT);
			User userAdded = us.addUser(u);
			Assertions.assertEquals(u.getLastName(), userAdded.getLastName());
			
		}

		@Test
		@Order(2)
		public void testUpdateUser() throws ParseException {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date d = dateFormat.parse("1995-11-08");
			User u = new User(5L,"mohamed" , "issa " , d , Role.CHEF_DEPARTEMENT);
			User userupdated = us.updateUser(u) ;
			Assertions.assertEquals(u.getLastName(), userupdated.getLastName());
		}
		
		@Test
		public  void testRetrieveAllUseres() {
			List<User> users = us.retrieveAllUsers();
			int i=0;
			for (User user : users){
				l.debug("user : "+user.getLastName());
				i++;
				
			}
			
			l.info("total "+i);
			Assertions.assertEquals(i, users.size());
				}
		@Test
		public void testRetrieveUser(){
			l.info("Trying to get id:2 user : ");
			try{
				
			User userRetrieved = us.retrieveUser("2");
			Assertions.assertEquals(2L, userRetrieved.getId());
			}
			catch (Exception e){
				l.error("User 2 doesn't exist");
			}
					
			}
			

		@Test
		public void testAll(){
			try{
				l.info("In testAll()");
				testAddUser();
				//testDeleteUser();
				
			}catch(Exception e){
				l.error("out of testAll() with errors : "+ e);
			}
			
		}
		
	

}
