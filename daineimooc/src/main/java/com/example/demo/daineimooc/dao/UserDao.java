package com.example.demo.daineimooc.dao;

import com.example.demo.daineimooc.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Mapper层映射数据库sql语句(USERSQLMapper.xml)
 */
@Mapper
public interface UserDao {

    /**
     * 获取搜索数据
     * @return
     */
    List<User> getAll();

    /**
     * 查询指定数据
     * @param id
     * @return
     */
    User selectUser(Integer id);

    /**
     * 插入数据
     * @param user
     * @return
     */
    int   insertUser(User user);

    /**
     * 删除某条数据
     * @param id
     * @return
     */
    int   deleteUser(Integer id);

    /**
     * 更新指定数据
     * @param user
     * @return
     */
    int  updateUser(User user);





}
