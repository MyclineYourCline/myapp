package com.example.ungdungchplay.Presenter;

import com.example.ungdungchplay.InterfaceManager.ScreenInterFace;

public class ScreenPresenter {
    private ScreenInterFace screenInterFace;

    public ScreenPresenter(ScreenInterFace screenInterFace) {
        this.screenInterFace = screenInterFace;
    }

    public void skip(){
        screenInterFace.skip();
    }
   public void next(){
        screenInterFace.next();
    }
   public void checkLogin(){
        screenInterFace.checkLogin();
    }
}
