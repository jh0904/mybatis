package test;

import cn.dao.UserDao;
import cn.dao.UserDaoImpl;
import cn.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * test
 *
 * @author jh
 * @date 2018/7/13 10:09
 * description:
 */
public class MethodTest {

    private SqlSessionFactory  sqlSessionFactory;
    @Before
    public void setUp() throws Exception{
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
        UserDao userDao=new UserDaoImpl (sqlSessionFactory);

        User user = userDao.findUserById (1);
        System.out.println (user);
    }
}
