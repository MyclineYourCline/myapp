package com.example.ungdungchplay.Presenter.ActivityPresenter;

import static android.util.Log.d;

import android.content.Context;

import com.example.ungdungchplay.Database.DbStruct;
import com.example.ungdungchplay.Database.UserDAO;
import com.example.ungdungchplay.InterfaceManager.ActivityInterface.RegisterInterface;
import com.example.ungdungchplay.ModelManager.User;

import java.util.List;

public class RegisterPresenter {
    private RegisterInterface registerInterface;
    private Context context;
    private UserDAO userDAO;
    private long checkInsert;
    private String message;

    public RegisterPresenter(RegisterInterface registerInterface , Context context) {
        this.registerInterface = registerInterface;
        this.context = context;
        userDAO = new UserDAO(context);
    }

    public void register(User user){
        List<User> list = userDAO.getAll();
        if (list.size() == 0){
                checkInsert = userDAO.insertUser(user);
                if (checkInsert == DbStruct.INSERT_ERR){
                    message = "Cannot add: "+user.getName();
                    registerInterface.RegisterError(message);
                }
                else{
                    registerInterface.registerSuccess();
                }
        }
        else{
            boolean AccountAlready = true;
            for (User x: list){
                if (x.getPhone().equals(user.getPhone())){
                    AccountAlready = false;
                }
                else{
                    AccountAlready = true;
                }
            }
            if (AccountAlready){
                checkInsert = userDAO.insertUser(user);
                if (checkInsert == DbStruct.INSERT_ERR ){
                    message = "Cannot add: "+user.getName();
                    registerInterface.RegisterError(message);
                    return;
                }
                registerInterface.registerSuccess();
            }
            else{
                message = "Account already exists: "+user.getPhone();
                registerInterface.RegisterError(message);
            }
        }
    }
}
