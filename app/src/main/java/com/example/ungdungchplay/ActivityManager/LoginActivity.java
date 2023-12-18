package com.example.ungdungchplay.ActivityManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ungdungchplay.InterfaceManager.LoginInterface;
import com.example.ungdungchplay.Presenter.LoginPresenter;
import com.example.ungdungchplay.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginInterface {
    private EditText edt_account, edt_passWord;
    private CheckBox cb_remember;
    private TextView txt_forgotPass, txt_signup;
    private Button btn_login;
    private LoginPresenter presenter;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unitUI();
    }
    private void unitUI(){
        presenter = new LoginPresenter(this);
        edt_account = findViewById(R.id.Login_edtAccount);
        edt_passWord = findViewById(R.id.Login_edtPassword);
        cb_remember = findViewById(R.id.Login_cbRemember);
        txt_forgotPass = findViewById(R.id.Login_txtForgot);
        txt_signup = findViewById(R.id.Login_txtRegister);
        btn_login = findViewById(R.id.Login_btnLogin);
        //
        btn_login.setOnClickListener(this);
        txt_signup.setOnClickListener(this);
        txt_forgotPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Login_btnLogin:
                presenter.login();
                break;
            case R.id.Login_txtRegister:
                intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.Login_txtForgot:
                intent = new Intent(this,ForgotPasswordActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginError() {

    }
}