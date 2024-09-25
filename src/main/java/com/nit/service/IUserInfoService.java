package com.nit.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nit.entity.UserInfo;


public interface IUserInfoService extends UserDetailsService {
       public  String register(UserInfo user);
}
