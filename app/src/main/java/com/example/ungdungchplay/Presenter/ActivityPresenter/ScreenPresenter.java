package com.example.ungdungchplay.Presenter.ActivityPresenter;

import com.example.ungdungchplay.InterfaceManager.ActivityInterface.ScreenInterFace;

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
   public void checkActive (boolean active){
        if (active) screenInterFace.activeSuccess();
        else screenInterFace.activeErr();

   }
}
