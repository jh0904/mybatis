package cn.first;

import cn.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * cn.first
 *
 * @author jh
 * @date 2018/7/12 21:23
 * description:
 */
public class MybatisFirst {
    /**
     * 根据id查询用户信息，得到一条信息.
     */
    @Test
    public void findUserById() throws IOException {
        String resource = "config/SqlMapConfig.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream (resource);

        // 创建会话工厂，传入mybatis的配置文件信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder ()
                .build (inputStream);

        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession ();

        // 通过SqlSession操作数据库
        // 第一个参数：映射文件中statement的id，等于=namespace+"."+statement的id
        // 第二个参数：指定和映射文件中所匹配的parameterType类型的参数
        // sqlSession.selectOne结果 是与映射文件中所匹配的resultType类型的对象
        // selectOne查询出一条记录
        User user = sqlSession.selectOne ("test.findUserById", 1);

        System.out.println (user);

        // 释放资源
        sqlSession.close ();
    }
    /**
     * 模糊查询，根据姓名查询。
     */
    @Test
    public void findUserByName() throws IOException {
        String resource = "config/SqlMapConfig.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream (resource);

        // 创建会话工厂，传入mybatis的配置文件信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder ()
                .build (inputStream);

        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession ();
        List<User> list = sqlSession.selectList ("test.findUserByName", "小明");

        for (User user : list) {
            System.out.println (user);
        }
        // 释放资源
        sqlSession.close ();
    }
    /**
     * 插入用户信息
     */
    @Test
    public void insertUser() throws IOException {
        String resource = "config/SqlMapConfig.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream (resource);

        // 创建会话工厂，传入mybatis的配置文件信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder ()
                .build (inputStream);

        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession ();
        User user = new User ( "heheda", new java.util.Date (), "女", "梦想中心");
        sqlSession.insert ("test.insertUser", user);
        sqlSession.commit ();
        //获取用户主键信息
        System.out.println (user.getId ());

        // 释放资源
        sqlSession.close ();
    }


    @Test
    public void deleteUser() throws IOException {
        String resource = "config/SqlMapConfig.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream (resource);

        // 创建会话工厂，传入mybatis的配置文件信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder ()
                .build (inputStream);

        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession ();
        sqlSession.delete ("test.deleteUser", 27);
        sqlSession.commit ();
        // 释放资源
        sqlSession.close ();
    }

    @Test
    public void updateUser() throws IOException {
        String resource = "config/SqlMapConfig.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream (resource);

        // 创建会话工厂，传入mybatis的配置文件信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder ()
                .build (inputStream);

        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession ();
        User user = new User ( 28,"magic", new java.util.Date (), "男", "西安工业大学");
        sqlSession.update ("test.updateUser", user);
        sqlSession.commit ();
        // 释放资源
        sqlSession.close ();
    }

}
