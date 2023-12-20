package com.example.ungdungchplay.Presenter;

import static android.util.Log.d;

import android.content.Context;

import com.example.ungdungchplay.Adapter.RoomAdapter;
import com.example.ungdungchplay.Database.DbStruct;
import com.example.ungdungchplay.Database.RoomDAO;
import com.example.ungdungchplay.InterfaceManager.FragmentHomeInterface;
import com.example.ungdungchplay.InterfaceManager.RoomListener;
import com.example.ungdungchplay.ModelManager.Room;

import java.util.List;

public class FragmentHomePresenter implements RoomListener {
    private Context context;
    private FragmentHomeInterface homeInterface;
    private RoomDAO roomDAO;
    private RoomAdapter adapter;

    public FragmentHomePresenter(Context context, FragmentHomeInterface homeInterface) {
        this.context = context;
        this.homeInterface = homeInterface;
        roomDAO = new RoomDAO(context);
        adapter = new RoomAdapter(context,this);
    }
    public void getData(){
        List<Room> list = roomDAO.getAll();
        if (list == null) homeInterface.dataErr(DbStruct.MESSAGE_ERR);
        else{
            homeInterface.dataExists(list);
            adapter.setList(list);
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
    public void query (String query){
        adapter.getFilter().filter(query);
    }

    @Override
    public void sendData(int option, Room room) {

    }
}
