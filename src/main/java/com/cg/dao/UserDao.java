package com.cg.dao;

import com.cg.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigInteger;
import java.util.List;

public interface UserDao extends JpaRepository<User, BigInteger> {
    public User findByUserName(String name);
    public User findByEmail(String email);
    public List<User> findByUserType(String type);
}
