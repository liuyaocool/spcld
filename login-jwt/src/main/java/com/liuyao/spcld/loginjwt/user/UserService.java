package com.liuyao.spcld.loginjwt.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User getUser(User uname) {
        User user = userMapper.getUser(uname);
        return user;
    }
}
