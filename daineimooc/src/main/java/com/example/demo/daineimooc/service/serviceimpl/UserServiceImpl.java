package com.example.demo.daineimooc.service.serviceimpl;

import com.example.demo.daineimooc.dao.UserDao;
import com.example.demo.daineimooc.entity.User;
import com.example.demo.daineimooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    /**
     * 注入UserDao接口
     */
    @Resource
    private UserDao userDao;

    /**
     * 实现UserService中的方法
     * @return
     */
    @Override
    public List<User> getAll() {
        return userDao.getAll() ;
    }

    @Override
    public User selectUser(Integer id) {
        return userDao.selectUser(id);
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return userDao.deleteUser(id);
    }

    @Override
    public int updateUser(User user) {
         return userDao.updateUser(user);
    }
}
