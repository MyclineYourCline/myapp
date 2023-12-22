package com.example.ungdungchplay.Presenter.FragmentPresenter;

import static android.util.Log.d;

import android.content.Context;

import com.example.ungdungchplay.Database.DbStruct;
import com.example.ungdungchplay.Database.RoomDAO;
import com.example.ungdungchplay.InterfaceManager.FragmentInterface.FragmentHomeInterface;
import com.example.ungdungchplay.InterfaceManager.SendData.RoomListener;
import com.example.ungdungchplay.ModelManager.Room;

import java.util.List;

public class FragmentHomePresenter implements RoomListener {
    private Context context;
    private FragmentHomeInterface homeInterface;
    private RoomDAO roomDAO;

    public FragmentHomePresenter(Context context, FragmentHomeInterface homeInterface) {
        this.context = context;
        this.homeInterface = homeInterface;
        roomDAO = new RoomDAO(context);
    }
    public void getData(){
        List<Room> list = roomDAO.getAll();
        if (list == null) homeInterface.dataErr(DbStruct.MESSAGE_ERR);
        else{
            homeInterface.dataExists(list);
        }
    }
    public void  addRoom (Room room){
        Room room1 = roomDAO.getByName(room.getName());
        if (room1 == null){
            long checkI = roomDAO.insertRoom(room);
            if ( checkI == DbStruct.INSERT_ERR) homeInterface.addRoomErr("add new Room fail");
            else homeInterface.addRoomSuccess("add new Room Successful", roomDAO.getAll());
        }
        else {
            homeInterface.addRoomErr("already exists: "+room.getName());
        }
    }

    @Override
    public void sendData(int option, Room room) {

    }
}
