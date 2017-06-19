package com.zgh.MVPDemo_Login.presenter;

import android.util.Log;

import com.zgh.MVPDemo_Login.bean.UserBean;
import com.zgh.MVPDemo_Login.contract.UserContract;
import com.zgh.MVPDemo_Login.model.UserModel;

/**
 * Created by ZGH on 2017/6/16.
 */

/**
 * (4)Presenter:它能拥有m和v层的接口实例
 * Presenter就能通过接口与View及Model进行交互了：
 * 主要就是save和load两个方法的具体逻辑，在两个方法中调用m和v层的接口中规定好的方法
 */
public class UserPresenter implements UserContract.Presenter {
    private UserContract.View mView;
    private UserContract.Model mModel;

    //将带有具体方法实现的实例 upcast 成为接口
    public UserPresenter(UserContract.View view) {
        this.mView = view;
        mModel = new UserModel();
    }

    //此方法将view层获取的数据存入model层，且只用到了接口里的方法
    @Override
    public void saveUser() {
        if (mView.getUserName() == null || mView.getUserPassword() == null) {
            Log.d("test", "saveUser: 账号或者密码不能为空");
        } else {
            mModel.saveUser(mView.getID(), mView.getUserName(), mView.getUserPassword());
            Log.d("test", "saveUser: success!");
        }
    }

    @Override
    public void loadUser() {
        UserBean user = mModel.loadUser(mView.getID());
        if (user != null) {
            mView.setUserName(user.getName());
            mView.setUserPassword(user.getPassword());
            Log.d("test", "loadUser: success!");
        } else {
            Log.d("test", "loadUser: 编号为id的用户数据不存在");
        }
    }
}
