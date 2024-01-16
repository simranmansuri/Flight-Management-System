package com.cg.controller;

import com.cg.bean.User;
import com.cg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;
    
    /*
     URI : http://localhost:9001/user/showAllUsers
     METHOD : GET
     */
    @GetMapping("/showAllUsers")
    public List<User> showAllUser(@RequestParam String userType){
        List<User> list =userService.viewUser(userType);
        return  list;
    }
//    @GetMapping("/showByuserName/{userName}")
//    public

    
    /*
     URI : http://localhost:9001/user/addUser
     METHOD : POST
     {
  		"email": "lakshya@gmail.com",
  		"id": 1,
  		"userName": "Lakshya",
  		"userPassword": "lakshya",
  		"userPhone": 1234567894,
  		"userType": "customer"
	}
     */
    @PostMapping("/addUser")
    public User newUser(@RequestBody User user){
    	userService.validateUser(user);
        return  userService.addUser(user);
    }
    
    /*
     URI : http://localhost:9001/user/showById/15
     METHOD : GET
     */
    @GetMapping("/showById/{userId}")
    public User showById(@PathVariable BigInteger userId){
    	
        return  userService.viewUser(userId);
    }
    
    /*
     URI : http://localhost:9001/user/modifyUser/11
     METHOD : PUT
     {
  		"email": "ram@gmail.com",
  		"id": 11,
  		"userName": "Ram",
  		"userPassword": "ram",
  		"userPhone": 1234567895,
  		"userType": "admin"
	}
     */
    @PutMapping("/modifyUser/{userId}")
    public User updateUser(@PathVariable BigInteger userId, @RequestBody User newU){
    	
    	userService.validateUser(newU);
        return userService.updateUser(userId,newU);
    }
    
    /*
     URI : http://localhost:9001/user/deleteUser/13
     METHOD : DELETE
     */
    @DeleteMapping("/deleteUser/{userId}")
    public  void deleteUser(@PathVariable String userId){
    	
        userService.deleteUser(new BigInteger(userId));
    }
    @GetMapping("/showByUserName/{Name}")
    public User showByUserName(@PathVariable String Name){
        User u =userService.viewByUserName(Name);

        return  u;

    }
    @GetMapping("/showByEmail/{Email}")
    public User showByEmail(@PathVariable String Email){
        User u =userService.viewByEmail(Email);

        return  u;

    }
    @PatchMapping("/patchUser/{id}")
    public User patchUser(@PathVariable BigInteger id ,@RequestBody User u){
        return userService.patchUser(id,u);

    }
    
    @GetMapping("/loginUser")
    public String loginUser(@RequestParam String userName, @RequestParam String userPassword){
        return userService.loginUser(userName, userPassword);
    }	
}
