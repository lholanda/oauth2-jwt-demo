package com.devsuperior.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.entities.Role;
import com.devsuperior.demo.entities.User;
import com.devsuperior.demo.projections.UserDetailsProjection;
import com.devsuperior.demo.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // faz uma consulta diferente, mas evitamos mudar FETCH
        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
        if(result.size() == 0){
            throw new UsernameNotFoundException("User not found !");
        }

        User user = new User();
        user.setName(result.get(0).getName());
        user.setEmail(username);
        user.setPassword(result.get(0).getPassword());

        result.stream()
               .map( projection -> new Role( projection.getRoleId(), projection.getAuthority() )) 
               .forEach(user::addRole);

        return user;
    }

}



/* 1a investida */
/*
 // 2 problemas 
// nao vai trazer o roles pois é muito, é lazy
// para resolver - //@ManyToMany(fetch = FetchType.EAGER) - nao é boa pratica


User user = repository.findByEmail(username);

        if(user == null){
            throw new UserNameNotFoundException("User not found !");
        }

        // 2 problemas 
        // nao vai trazer o roles pois é muito, é lazy
        // para resolver - //@ManyToMany(fetch = FetchType.EAGER) - nao é boa pratica 
*/

/*
 // so um teste, mas nao precisa fazer isso pois User sera preenchido abaixo 
        // System.out.println((repository.findByEmail(result
        //                     .get(0)
        //                     .getUsername())
        //                     .isEnabled() ? "enable" : "not enable") );



        System.out.println("--- " + user.isEnabled());
 */



 /*
      
        for(UserDetailsProjection projection : result){
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        
  */