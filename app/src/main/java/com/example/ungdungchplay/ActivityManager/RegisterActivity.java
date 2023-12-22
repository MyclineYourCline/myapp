package com.example.ungdungchplay.ActivityManager;

import static android.util.Log.d;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ungdungchplay.InterfaceManager.ActivityInterface.RegisterInterface;
import com.example.ungdungchplay.ModelManager.User;
import com.example.ungdungchplay.Presenter.ActivityPresenter.RegisterPresenter;
import com.example.ungdungchplay.R;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RegisterInterface {
    private ImageView img_back;
    private TextInputEditText edt_name, edt_account, edt_passWord, edt_rePassword;
    private Button btn_register;
    private TextView txt_login, txt_message;
    private RegisterPresenter presenter;
    private String name, password, account, rePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        unitUI();
    }
    private void  unitUI(){
        img_back = findViewById(R.id.Register_imgBack);
        edt_name = findViewById(R.id.Register_edtName);
        edt_account = findViewById(R.id.Register_edtAccount);
        edt_passWord = findViewById(R.id.Register_edtPassword);
        edt_rePassword = findViewById(R.id.Register_edtRepass);
        btn_register = findViewById(R.id.Register_btnRegister);
        txt_login = findViewById(R.id.Register_txtLogin);
        btn_register.setOnClickListener(this);
        txt_login.setOnClickListener(this);
        img_back.setOnClickListener(this);
        txt_message = findViewById(R.id.Register_txtMessage);
        presenter = new RegisterPresenter(this,this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Register_btnRegister:
                    RegisterOnClick();
                break;
            case R.id.Register_txtLogin:
                loginHereOnclick();
                break;
            case R.id.Register_imgBack:
                finish();
                break;
            default:
                break;
        }
    }
    @SuppressLint("SetTextI18n")
    void RegisterOnClick (){
         name = edt_name.getText().toString().trim();
         account = edt_account.getText().toString().trim();
         password = edt_passWord.getText().toString().trim();
         rePassword = edt_rePassword.getText().toString().trim();
        if (name.isEmpty() || account.isEmpty() || password.isEmpty()|| rePassword.isEmpty()){
            txt_message.setText("Cannot be empty");
            txt_message.setVisibility(View.VISIBLE);
            return;
        }
        else{
            if (!rePassword.equals(password)){
                txt_message.setText("Password invalid ");
                txt_message.setTextColor(getColor(R.color.error));
                txt_message.setVisibility(View.VISIBLE);
            }
            else{
                User user = new User();
                user.setName(edt_name.getText().toString().trim());
                user.setPhone(edt_account.getText().toString().trim());
                user.setPassword(edt_rePassword.getText().toString().trim());
                presenter.register(user);
            }

        }
    }
    private void loginHereOnclick(){
        User user = new User();
        user.setPhone(account);
        user.setPassword(rePassword);
        Intent intent  = new Intent(this,LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        intent.putExtras(bundle);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    public void registerSuccess() {
        txt_message.setText("Successfully created account");
        txt_message.setTextColor(getColor(R.color.success));
        txt_message.setVisibility(View.VISIBLE);
        edt_name.setText(null);
        edt_passWord.setText(null);
        edt_rePassword.setText(null);
        edt_account.setText(null);
    }

    @Override
    public void RegisterError(String message) {
        txt_message.setText(message);
        txt_message.setTextColor(getColor(R.color.error));
        txt_message.setVisibility(View.VISIBLE);
    }
}