package com.zss98.AuthorityResolving.Service;

import com.zss98.AuthorityResolving.Entity.User;
import com.zss98.AuthorityResolving.Entity.UserData;
import com.zss98.AuthorityResolving.Mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserMapper mapper;

    public UserService(UserMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user =  mapper.selectUserByName(s);
        return user;
    }

    public List getAllUser(){
        return mapper.getAllUser();
    }

    public UserData getUserInfo(String user) {
        return mapper.getUserInfo(user);
    }
}
