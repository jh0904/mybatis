package cn.dao;

import cn.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * cn.dao
 *
 * @author jh
 * @date 2018/7/13 9:59
 * description:
 */
public class UserDaoImpl implements UserDao {
    //注入构造函数SqlSessionFactory

    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User findUserById(int id) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession ();
        User o = sqlSession.selectOne ("test.findUserById", 1);
        sqlSession.close ();
        return o;
    }

    @Override
    public void insertUser(User User) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession ();
        sqlSession.insert ("test.insertUser", User);
        sqlSession.commit ();
        sqlSession.close ();
    }

    @Override
    public void deleteUser(int id) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession ();
        sqlSession.delete ("test.deleteUser", id);
        sqlSession.commit ();
        sqlSession.close ();

    }
}
