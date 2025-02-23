package com.devsuperior.demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.devsuperior.demo.entities.Role;
import com.devsuperior.demo.entities.User;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	// @Autowired
	// private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@SuppressWarnings("unused")
	@Override
	public void run(String... args) throws Exception {

        String corsOrigins = "http://localhost:3000,http://localhost:5173";

		String[] origins = corsOrigins.split(",");
		Arrays.asList(origins).forEach(System.out::println);

		// for(String or : origins){
		// 	System.out.println(or);
		// }

		//Arrays.stream(origins).map( x -> x).collect(Collectors.joining(" 1 "));
		

		if (false) {
			User user = new User(); // depois inicializar um user e verificar se ha rules atraves do user.hasRule() pode simular tudo pelo USER ,, substituir tudo acima
			
			Set<Role> roles = new HashSet<>();
			roles.add(new Role("ROLE_OPERATOR"));
			roles.add(new Role("ROLE_ADMIN"));
			//roles.stream().map(Role::getAuthority).forEach(System.out::println);	
			
			//System.out.println("Resposta = "+ hasRole("ROLE_OPERATOR", roles) );

			// trecho que irei utilizar no LOGIN
			
			System.out.println("Encode = " + passwordEncoder.encode("123456"));
			boolean result = passwordEncoder.matches("123456","$2a$10$Fs45JHQ79jLKGA2AVR4Y5uKdfIiFIRdx/AhRgArip6GpbUguGv9j2");
			System.out.println("Resultado = " + result);
		}

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