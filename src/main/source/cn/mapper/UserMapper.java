package cn.mapper;

import cn.pojo.User;
import cn.pojo.UserCustom;
import cn.pojo.UserQueryVo;

import java.util.List;

/**
 * cn.mapper
 *
 * @author jh
 * @date 2018/7/13 10:16
 * description:
 */
public interface UserMapper {
    /**
     * 用户信息的综合查询
     */
    public List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;

    /**
     * 用户信息的综合查询总数
     */
    public int findUserCount(UserQueryVo userQueryVo) throws Exception;
    /**
     * 用户信息的查询（ResultMap）
     */
    public User findUserByIResultMap(int i) throws Exception;

    /**
     * 根据id查询用户信息
     */
    public User findUserById(int id) throws Exception;

    /**
     * 根据姓名模糊查询用户 代理对象内部selectList
     */

    public List<User> findUserByName(String name) throws Exception;

    /**
     * 添加用户信息
     */
    public void insertUser(User User) throws Exception;

    /**
     * 删除用户信息
     */
    public void deleteUser(int id) throws Exception;
}
