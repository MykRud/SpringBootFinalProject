package com.spring_final.SpringFinalProject;

import com.spring_final.SpringFinalProject.model.Role;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

@SpringBootApplication
public class SpringFinalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFinalProjectApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.US);
		return resolver;
	} // maybe has to be moved to @Configuration class

	/*@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "USER"));
			userService.saveRole(new Role(null, "ADMIN"));

			userService.addUser(new User(null, "John", "Travolta", "john", "1234", 59, "+380980689690", "Male", new ArrayList<>(), new HashSet<>(), new HashSet<>()));
			userService.addUser(new User(null, "Will", "Smith", "will", "1234", 63, "+380980689690", "Male", new ArrayList<>(), new HashSet<>(), new HashSet<>()));
			userService.addUser(new User(null, "Jim", "Carry", "jim", "1234", 58, "+380980689690", "Male", new ArrayList<>(), new HashSet<>(), new HashSet<>()));
			userService.addUser(new User(null, "Tom", "Haaland", "tom", "1234", 26, "+380980689690", "Male", new ArrayList<>(), new HashSet<>(), new HashSet<>()));

			userService.addRoleToUser("john", "USER");
			userService.addRoleToUser("john", "ADMIN");
			userService.addRoleToUser("will", "USER");
			userService.addRoleToUser("jim", "USER");
			userService.addRoleToUser("tom", "USER");
			userService.addRoleToUser("tom", "ADMIN");
		};
	}*/
}
