package com.emyus.auth;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emyus.user.UserRequest;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HttpSession session;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		User user = userRepository.identifyUser(id);
		if (user == null) {
			throw new UsernameNotFoundException(id + "is not found");
		} else {
			session.setAttribute("id", id);
			session.setAttribute("admin_flag", user.getAdminFlag());
			return user;
		}
	}

	public User getOne(String username) {
		return userRepository.getOne(username);
	}

	public List<User> findAll() {
		return userRepository.findAll();

	}
	public void create(UserRequest userRequest) {
	}

}
