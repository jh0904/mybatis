package cn.dao;

import cn.pojo.User;

/**
 * cn.dao
 *
 * @author jh
 * @date 2018/7/13 9:55
 * description:
 */
public interface UserDao {
    /**根据id查询用户信息*/
    public User findUserById(int id) throws Exception;
   /**添加用户信息*/
    public void insertUser(User User) throws Exception;
   /**删除用户信息*/
    public void deleteUser(int id) throws Exception;
}
