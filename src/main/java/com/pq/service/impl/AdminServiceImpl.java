package com.pq.service.impl;

import com.pq.dao.UserMapper;
import com.pq.pojo.User;
import com.pq.service.AdminService;
import com.pq.utils.ResultContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultContent admin(User u) {
        List<User> users = userMapper.selectAll();
        for (User user:users) {
              if(u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword())) {
                  return new ResultContent(0, "", user);
              }
        }
        return new ResultContent(1, "", null);
    }

    @Override
    public ResultContent update(User u) {
        return userMapper.updatePassword(u);
    }

}
