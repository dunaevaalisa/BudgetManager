package s24.budgetmanager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import s24.budgetmanager.domain.AppUser;
import s24.budgetmanager.domain.AppUserRepository;

/**
 * Service class implementing the UserDetailsService interface for handling user details retrieval.
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService  {
	@Autowired 
	AppUserRepository repository;

    // Method to load user details by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
    	AppUser curruser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }   
} 
