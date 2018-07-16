package cn.mapper;

import cn.pojo.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * cn.mapper
 *
 * @author jh
 * @date 2018/7/13 10:47
 * description:
 */
public class MapperTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        //创建SqlSessionFactory
        String resource = "config/SqlMapConfig.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream (resource);

        // 创建会话工厂，传入mybatis的配置文件信息
        sqlSessionFactory = new SqlSessionFactoryBuilder ()
                .build (inputStream);
    }

    @Test
    public void findUserById() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession ();
        //创建UserMapper对象，自动生成mapper代理对象 
        UserMapper userMapper = sqlSession.getMapper (UserMapper.class);

        User user = userMapper.findUserById (28);

        System.out.println (user);
    }

    @Test
    public void findUserList() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession ();
        //创建UserMapper对象，自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper (UserMapper.class);

        UserQueryVo userQueryVo = new UserQueryVo ();

        UserCustom userCustom = new UserCustom ();
        userCustom.setSex ("1");
        userCustom.setUsername ("小");
        List<Integer> list = new ArrayList<> ();
        list.add (1);
        list.add (10);
        list.add (16);
        userQueryVo.setIds (list);
        userQueryVo.setUserCustom (userCustom);

        List<UserCustom> user = userMapper.findUserList (userQueryVo);

        System.out.println (user);
    }

    @Test
    public void findUserCount() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession ();
        //创建UserMapper对象，自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper (UserMapper.class);

        UserQueryVo userQueryVo = new UserQueryVo ();

        UserCustom userCustom = new UserCustom ();
        userCustom.setSex ("1");
        userCustom.setUsername ("小");
        userQueryVo.setUserCustom (userCustom);

        int count = userMapper.findUserCount (userQueryVo);

        System.out.println (count);
    }

    @Test
    public void findUserByIResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession ();
        //创建UserMapper对象，自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper (UserMapper.class);

        User userByIResultMap = userMapper.findUserByIResultMap (1);
        System.out.println (userByIResultMap);

        sqlSession.close ();

    }

    @Test
    public void findOrdersUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession ();
        //创建UserMapper对象，自动生成mapper代理对象
        OrderMapperCustom orderMapperCustom = sqlSession.getMapper (OrderMapperCustom.class);

        List<OrdersCustom> ordersUser = orderMapperCustom.findOrdersUser ();
        System.out.println (ordersUser);

        sqlSession.close ();

    }

    @Test
    public void findOrdersUserResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession ();
        //创建UserMapper对象，自动生成mapper代理对象
        OrderMapperCustom orderMapperCustom = sqlSession.getMapper (OrderMapperCustom.class);

        List<Orders> ordersUserResultMap = orderMapperCustom.findOrdersUserResultMap ();
        for (Orders orders : ordersUserResultMap) {
            System.out.println (orders);
        }
        sqlSession.close ();

    }

    /*测试findOrdersAndOrderDetailResultMap*/
    @Test
    public void findOrdersAndOrderDetailResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession ();
        //创建UserMapper对象，自动生成mapper代理对象
        OrderMapperCustom orderMapperCustom = sqlSession.getMapper (OrderMapperCustom.class);

        List<Orders> ordersUserResultMap = orderMapperCustom.findOrdersAndOrderDetailResultMap ();
        for (Orders orders : ordersUserResultMap) {
            System.out.println (orders);
        }
        sqlSession.close ();

    }

    /*测试findUserAndItemResultMap*/
    @Test
    public void findUserAndItemResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession ();
        //创建UserMapper对象，自动生成mapper代理对象
        OrderMapperCustom orderMapperCustom = sqlSession.getMapper (OrderMapperCustom.class);

        List<User> ordersUserResultMap = orderMapperCustom.findUserAndItemResultMap ();
        for (User orders : ordersUserResultMap) {
            System.out.println (orders);
        }
        sqlSession.close ();

    }

    /**
     * 查询订单关联用户，用户信息需要延迟加载。
     */
    @Test
    public void testFindOrdersUserLazyLoading() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession ();// 创建代理对象
        OrderMapperCustom ordersMapperCustom = sqlSession
                .getMapper (OrderMapperCustom.class);
        // 查询订单信息（单表）
        List<Orders> list = ordersMapperCustom.findOrdersUserLazyLoading ();
        // 遍历上边的订单列表
        for (Orders orders : list) {
            // 执行getUser()去查询用户信息，这里实现按需加载
            User user = orders.getUser ();
            System.out.println (user);
        }

    }

    /**
     * 一级缓存测试
     */
    @Test
    public void testCache() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession ();
        UserMapper userMapper = sqlSession.getMapper (UserMapper.class);
        User user1 = userMapper.findUserById (1);
        System.out.println (user1);

        /*清空缓存*/
        user1.setUsername ("magic_jh");
        userMapper.updateUser (user1);
        sqlSession.commit ();


        User user2 = userMapper.findUserById (1);
        System.out.println (user2);

        sqlSession.close ();
    }


    /**
     * 二级缓存测试*/
    @Test
    public void testCache2() throws Exception {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        // 创建代理对象
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        // 第一次发起请求，查询id为1的用户
        User user1 = userMapper1.findUserById(1);
        System.out.println(user1);

        //这里执行关闭操作，将sqlsession中的数据写到二级缓存区域
        sqlSession1.close();



  /*      //使用sqlSession3执行commit()操作
        UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
        User user  = userMapper3.findUserById(1);
        user.setUsername("张三");
        userMapper3.updateUser(user);
        //执行提交，清空UserMapper下边的二级缓存
        sqlSession3.commit();
        sqlSession3.close();*/



        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        // 第二次发起请求，查询id为1的用户
        User user2 = userMapper2.findUserById(1);
        System.out.println(user2);

        sqlSession2.close();

    }


}
