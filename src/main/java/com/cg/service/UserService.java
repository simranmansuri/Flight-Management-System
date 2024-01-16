package com.cg.service;

import com.cg.bean.User;
import java.math.BigInteger;
import java.util.List;

public interface UserService {
	
	//add a user
    public  User addUser(User u);
    
    //update user based on user id
    public User updateUser(BigInteger userId,User newAccount);
    
    //delete user
    public void deleteUser(BigInteger userId);
    
    //view user based on user id
    public User viewUser(BigInteger userId);
    
    //view all users
    public List<User> viewUser(String type);
    
    //validate user
    public void validateUser(User user);

    public User viewByUserName(String Name);

    public  User viewByEmail(String Email);
    public User patchUser(BigInteger userId,User newAccount);
    public String loginUser(String name, String password);
}
