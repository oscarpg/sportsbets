package com.rurallabs.sportsbets.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rurallabs.sportsbets.business.entities.User;
import com.rurallabs.sportsbets.business.services.UserService;

@Service
public class SBUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private UserService userService;
    
    @Transactional
    @Override
    /*
     * First search user by the login and if does not exists tries to find it by the email. 
     */
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        User user = this.userService.find(login);        
        if (user == null) {
        	user = this.userService.findByEmail(login);
        	if (user == null) {
        		throw new UsernameNotFoundException("User with username " + login + " not found");
        	}
        }
        
        final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (user.isGlobalAdmin()) {
        	grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));

		final UserDetails userDetails = new org.springframework.security.core.userdetails.User(
				user.getLogin(), user.getPassword(), grantedAuthorities);

        return userDetails;
    }

}
