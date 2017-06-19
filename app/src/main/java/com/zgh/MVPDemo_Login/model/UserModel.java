package com.zgh.MVPDemo_Login.model;

import android.util.SparseArray;

import com.zgh.MVPDemo_Login.bean.UserBean;
import com.zgh.MVPDemo_Login.contract.UserContract;

/**
 * Created by ZGH on 2017/6/16.
 */

/**
 * 数据存储的模型层，只需要考虑怎么把数据存起来
 */
public class UserModel implements UserContract.Model {
    //android的优化版hashmap，数据千条以下的时候效率高
    private SparseArray<UserBean> mArray = new SparseArray<>();

    @Override
    public void saveUser(int id, String name, String password) {
        //存入array
        mArray.append(id, new UserBean(name, password));
    }

    @Override
    public UserBean loadUser(int id) {
        if (mArray.indexOfKey(id) >= 0) {
            return mArray.get(id);
        } else {
            return null;
        }
    }
}
