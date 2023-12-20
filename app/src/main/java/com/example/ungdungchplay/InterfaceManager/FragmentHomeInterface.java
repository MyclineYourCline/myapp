package com.example.ungdungchplay.InterfaceManager;

import com.example.ungdungchplay.ModelManager.Room;

import java.util.List;

public interface FragmentHomeInterface {
    void addRoomSuccess(String msg, List<Room> list);
    void addRoomErr(String msg);
    void dataExists(List<Room> list);
    void dataErr(String msg);
    void query (List<Room> list);
}
