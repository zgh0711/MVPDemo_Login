package com.zgh.MVPDemo_Login;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.zgh.MVPDemo_Login.contract.UserContract;
import com.zgh.MVPDemo_Login.presenter.UserPresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MVP View层，控制页面逻辑，实现View层的接口里面的方法，
 * 并获取p层的接口实例，并且传入此v层,从而可以调用p层里的实现业务逻辑的方法
 */
public class LoginActivity extends AppCompatActivity implements UserContract.View{

    private static final String  EMAIL_PATTERN =
            "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private              Pattern pattern       = Pattern.compile(EMAIL_PATTERN);
    private AutoCompleteTextView mEmailView;
    private EditText             mPasswordView;

    private UserPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        //获取p层的接口实例，并且传入此v层,为了调用p层里的实现业务逻辑的方法
        mPresenter = new UserPresenter(this);

        Button button = (Button) findViewById(R.id.email_sign_in_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();

                String name = mEmailView.getText().toString();
                String pass = mPasswordView.getText().toString();
                if (!validateEmail(name)) {
                    mEmailView.setError("Email Error");
                } else if (!validatePassword(pass)) {
                    mPasswordView.setError("Password Error");
                }

                mPresenter.saveUser();
            }
        });

        Button btn_read = (Button) findViewById(R.id.btn_read);
        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.loadUser();
            }
        });
    }

    public boolean validateEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password) {
        return password.length() > 5;
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(),
                                                                           InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public String getUserName() {
        return mEmailView.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return mPasswordView.getText().toString();
    }

    @Override
    public void setUserName(String userName) {
        mEmailView.setText(userName);
    }

    @Override
    public void setUserPassword(String userPassword) {
        mPasswordView.setText(userPassword);
    }
}

