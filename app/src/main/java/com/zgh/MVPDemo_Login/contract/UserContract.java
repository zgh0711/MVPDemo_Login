package com.zgh.MVPDemo_Login.contract;

import com.zgh.MVPDemo_Login.bean.UserBean;

/**
 * Created by ZGH on 2017/6/16.
 */

public interface UserContract {
    /**
     * (3)Model接口：
     * 同样，Model也需要对这三个字段进行读写操作，并存储在某个载体内(这不是我们所关心的，
     * 可以存在内存、文件、数据库或者远程服务器，但对于Presenter及View无影响),定义IUserModel接口：
     */
    interface Model {
        void saveUser(int id, String name, String password);

        UserBean loadUser(int id);
    }

    /**
     * (2)再来看看View接口：
     * 根据需求可知，save按钮按下后，会把ID、FirstName、LastName这三个EditText里的数据传给model进行储存，由于
     * 我们是mvp架构，所以是传给presenter层让它和model层打交道。所以要有读取view组件里数据的3个方法;
     * load按钮按下后，读取id数值，然后传给p层，让它从m层取出对应的用户名密码，然后返回数据给v层并且显示出来，
     * 所以要有把text set进两个edittext里的方法。
     */
    interface View {
        int getID();

        String getUserName();

        String getUserPassword();

        void setUserName(String userName);

        void setUserPassword(String userPassword);
    }

    /**
     * 新增p层接口，原本v层是新建一个p层实例，现在更加抽象化，让v层只能持有一个p层的接口实例
     * p层应该实现的2个方法
     */
    interface Presenter {
        void saveUser();

        void loadUser();
    }
}
