package com.nit.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nit.entity.UserInfo;
import com.nit.repo.IUserInfoRepository;

@Service("userService")
public class UserInfoServiceImpl implements IUserInfoService {
	@Autowired
	private IUserInfoRepository  userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  //  get Model/Entity class object
		 Optional<UserInfo> opt=userRepo.findByUname(username);
		  if(opt.isEmpty())
			   throw new  IllegalArgumentException("invalid inputs");
	     
		  UserInfo info=opt.get();
		 //get Roles  from model class object
		   Set<String> roles=info.getRoles();
		 // Convert   Set<String> roles to  Set<SGA> object
		   Set<GrantedAuthority> sgaRoles=new HashSet();
		   for(String role:roles) {
			    SimpleGrantedAuthority authority=new SimpleGrantedAuthority(role);
			    sgaRoles.add(authority);
		   }
		   
		 //convert   Model class obj into  User object
		      User user=new User(info.getUname(), info.getPwd(), sgaRoles);
		return  user;
	}

	@Override
	public String register(UserInfo user) {
		
		 userRepo.save(user);
		 return "user registered";
	}

}
