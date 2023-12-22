package com.example.ungdungchplay.ActivityManager;

import static android.util.Log.d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ungdungchplay.InterfaceManager.ActivityInterface.LoginInterface;
import com.example.ungdungchplay.ModelManager.User;
import com.example.ungdungchplay.Presenter.ActivityPresenter.LoginPresenter;
import com.example.ungdungchplay.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginInterface {
    private EditText edt_account, edt_passWord;
    private CheckBox cb_remember;
    private TextView txt_forgotPass, txt_signup, txt_message;
    private Button btn_login;
    private LoginPresenter presenter;
    private Intent intent;
    private SharedPreferences sharedPreferences;
    private  SharedPreferences.Editor editor;
    public static final String ACTIVE_LOGIN = "login_active";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(ScreenActivity.SHARE_NAME,MODE_PRIVATE);
        editor = sharedPreferences.edit();
        unitUI();
        getActive();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        txt_message.setVisibility(View.GONE);
    }

    private void unitUI(){
        presenter = new LoginPresenter(this, this);
        edt_account = findViewById(R.id.Login_edtAccount);
        edt_passWord = findViewById(R.id.Login_edtPassword);
        cb_remember = findViewById(R.id.Login_cbRemember);
        txt_forgotPass = findViewById(R.id.Login_txtForgot);
        txt_signup = findViewById(R.id.Login_txtRegister);
        btn_login = findViewById(R.id.Login_btnLogin);
        //
        txt_message = findViewById(R.id.Login_txtMessage);
        btn_login.setOnClickListener(this);
        txt_signup.setOnClickListener(this);
        txt_forgotPass.setOnClickListener(this);
        getDataForRegister();
        //
        txt_message.setVisibility(View.GONE);
    }
    private void getActive (){
        boolean active = sharedPreferences.getBoolean(LoginActivity.ACTIVE_LOGIN,false);
        presenter.LoginActive(active);
    }
    private void  getDataForRegister(){
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null){
            String account = sharedPreferences.getString("account","");
            String password = sharedPreferences.getString("password","");
            edt_account.setText(account);
            edt_passWord.setText(password);
            if (account.length() != 0) cb_remember.setChecked(true);
        return;
        }
        User user = (User) bundle.getSerializable("user");
        edt_account.setText(user.getPhone());
        edt_passWord.setText(user.getPassword());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Login_btnLogin:
                LoginOnclick();
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
    private void LoginOnclick (){
        if ( edt_account.getText().toString().isEmpty()
                || edt_passWord.getText().toString().isEmpty()){
            txt_message.setText("Cannot be empty");
            txt_message.setTextColor(getColor(R.color.error));
            txt_message.setVisibility(View.VISIBLE);
        }
        else{
            String account = edt_account.getText().toString().trim();
            String password = edt_passWord.getText().toString().trim();
            presenter.login(account,password);
        }
    }
    @Override
    public void loginSuccess(String message, User user) {
        txt_message.setText(message);
        txt_message.setTextColor(getColor(R.color.success));
        txt_message.setVisibility(View.VISIBLE);
        //
        intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        startActivity(intent);
        isRemember();
    }
    private void isRemember() {
        if (cb_remember.isChecked()) {
            editor.putString("account", edt_account.getText().toString().trim());
            editor.putString("password", edt_passWord.getText().toString().trim());
            editor.apply();
        } else {
            editor.putString("account","");
            editor.putString("password", " ");
            editor.apply();
        }
    }
    @Override
    public void loginError(String message) {
        txt_message.setText(message);
        txt_message.setTextColor(getColor(R.color.error));
        txt_message.setVisibility(View.VISIBLE);
    }

    @Override
    public void loginActiveTrue() {
        d("ca" + "chung", "loginActiveTrue: ");
    }

    @Override
    public void loginActiveFalse() {
        d("ca" + "chung", "loginActiveFalse: ");
    }

}