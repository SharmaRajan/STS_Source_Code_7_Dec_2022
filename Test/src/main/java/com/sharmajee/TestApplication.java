package com.sharmajee;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.sharmajee.entities.UserEntity;
import com.sharmajee.repository.UserRepository;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		//SpringApplication.run(TestApplication.class, args);
		ApplicationContext context = SpringApplication.run(TestApplication.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);
		
//		UserEntity user = new UserEntity();
//		user.setName("Rajan Sharma");
//		user.setCity("Samastipur");
//		user.setStatus("I am java programmer...!!!");
//		UserEntity savedUser =  userRepository.save(user);
//		System.out.println(savedUser);
		
		UserEntity user1 = new UserEntity();
		user1.setName("Ranjan");
		user1.setCity("Delhi");
		user1.setStatus("Python Programmer");
		
		UserEntity user2 = new UserEntity();
		user2.setName("Uttam");
		user2.setStatus("Java Programmer");
		user2.setCity("Biharsarif");
		
		List<UserEntity> savedUsers = List.of(user1,user2);
		userRepository.saveAll(savedUsers);
		
	}

}
