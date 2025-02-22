package com.devsuperior.demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.demo.entities.Role;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}



	
	@Override
	public void run(String... args) throws Exception {
		Set<Role> roles = new HashSet<>();

		roles.add(new Role("ROLE_OPERATOR"));
		roles.add(new Role("ROLE_ADMIN"));
		roles.stream().map(Role::getAuthority).forEach(System.out::println);	

		System.out.println("Resposta = "+ hasRole("ROLE_OPERATOR", roles) );
	}

	 // se contem a role, retorna true
	 public boolean hasRole(String roleName, Set<Role> roles) {
		return roles.contains(new Role(roleName));
	 }

}



        // Set<Integer> list = new HashSet<>(Arrays.asList(1,2,3,4,5,6,7));
		// list.stream().forEach(System.out::println);	
		// System.out.println("Resposta = "+(list.contains(31) ? true : false)); 
		// System.out.println("Resposta = "+ hasNumber(2, list) );
		/*
		 public boolean hasNumber(Integer number, Set<Integer> list){
		   return (list.contains(number) ? true : false); 
	     }
		*/