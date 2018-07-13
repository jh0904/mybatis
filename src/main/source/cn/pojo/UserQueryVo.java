package cn.pojo;

/**
 * cn.pojo
 *
 * @author jh
 * @date 2018/7/13 14:44
 * description:用户包装程序   po持久层对象vo视图层对象 pojo用户自定义对象
 */
public class UserQueryVo {
    /**
     * 用户查询条件*/
    private UserCustom userCustom;
    //包装订单、商品信息

    public UserCustom getUserCustom() {
        return userCustom;
    }

    public void setUserCustom(UserCustom userCustom) {
        this.userCustom = userCustom;
    }
}
