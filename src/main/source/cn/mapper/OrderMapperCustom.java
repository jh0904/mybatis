package cn.mapper;

import cn.pojo.Orders;
import cn.pojo.OrdersCustom;
import cn.pojo.User;

import java.util.List;

/**
 * cn.mapper
 *
 * @author jh
 * @date 2018/7/14 14:40
 * description:
 */
public interface OrderMapperCustom {
    public List<OrdersCustom> findOrdersUser() throws Exception;

    public List<Orders> findOrdersUserResultMap() throws Exception;

    public List<Orders> findOrdersAndOrderDetailResultMap() throws Exception;

    public List<User> findUserAndItemResultMap() throws Exception;

    public List<Orders> findOrdersUserLazyLoading() throws Exception;
}
