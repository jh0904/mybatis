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

        UserQueryVo userQueryVo=new UserQueryVo ();

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

        UserQueryVo userQueryVo=new UserQueryVo ();

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
}
