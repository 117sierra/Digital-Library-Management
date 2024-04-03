package com.gss.minor1.service;

import com.gss.minor1.Repository.CacheRepository;
import com.gss.minor1.Repository.UserRepository;
import com.gss.minor1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private CacheRepository cacheRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String contact) throws UsernameNotFoundException {
        User user =cacheRepository.getuserbycontact(contact);
        if(user!=null){
            return user;
        }
        user = userRepository.findByContact(contact);
        if(user==null){
            throw new UsernameNotFoundException("User not present in the system");
        }
        cacheRepository.insertDatabycontact(user);
        return user;

    }

}
