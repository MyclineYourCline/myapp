package com.example.ungdungchplay.InterfaceManager.ActivityInterface;

import com.example.ungdungchplay.ModelManager.Room;
import com.example.ungdungchplay.ModelManager.Table;

import java.util.List;
public interface TableActivityInterFace {
 void addTableSuccess(String msg, List<Table> list);
 void addTableErr(String msg);
 void dataExists(List<Table> list);
 void dataErr(String msg);
}
