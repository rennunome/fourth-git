package com.emyus.auth;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	HttpSession session;
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		User user = userRepository.identifyUser(id);
		if (user == null) {
			throw new UsernameNotFoundException(id + "is not found");
		} else {
			session.setAttribute("id", id);
			return user;
		}
	}

	public User getOne(String username) {
		return userRepository.getOne(username);
	}
}
