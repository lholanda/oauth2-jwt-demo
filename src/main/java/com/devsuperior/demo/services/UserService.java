package com.devsuperior.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.entities.Role;
import com.devsuperior.demo.entities.User;
import com.devsuperior.demo.projections.UserDetailsProjection;
import com.devsuperior.demo.repositories.UserRepository;
import com.devsuperior.demo.services.exceptions.UserNameNotFoundException;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName)  {
        // @ManyToMany(fetch = FetchType.EAGER) // tirar do roles do User
        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(userName);

        if( result.size() == 0 ){
            throw new UserNameNotFoundException("User not found !");
        }
        User user = new User();
        user.setName(result.get(0).getName());
        user.setEmail(userName);
        user.setPassword(result.get(0).getPassword());

        result.stream()
              .map(projection-> new Role(projection.getRoleId(), projection.getAuthority()))
              .forEach(user::addRole);
        /*      
        for(UserDetailsProjection projection : result){
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        */

        return user;
    }

}
