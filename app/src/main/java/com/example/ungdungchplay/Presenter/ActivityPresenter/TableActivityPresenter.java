package com.example.ungdungchplay.Presenter.ActivityPresenter;

import static android.util.Log.d;

import android.content.Context;

import com.example.ungdungchplay.Database.DbStruct;
import com.example.ungdungchplay.Database.TableDAO;
import com.example.ungdungchplay.InterfaceManager.ActivityInterface.TableActivityInterFace;
import com.example.ungdungchplay.ModelManager.Table;

import java.util.List;

public class TableActivityPresenter {
    private Context context;
    private TableActivityInterFace tableActivityInterFace;
    private TableDAO tableDAO;

    public TableActivityPresenter(Context context, TableActivityInterFace tableActivityInterFace) {
        this.context = context;
        this.tableActivityInterFace = tableActivityInterFace;
        tableDAO = new TableDAO(context);
    }
    public void getData(int roomID){
        List<Table> list = tableDAO.getByRoomID(roomID);
        if (list == null) tableActivityInterFace.dataErr(DbStruct.MESSAGE_ERR);
        else{
            tableActivityInterFace.dataExists(list);
        }
    }
    public void  addTable (Table table, int roomID){
        Table table1 = tableDAO.getByNameAndRoomID(table.getName(),roomID);
        if (table1 == null){
            long checkI = tableDAO.insertTable(table);
            if ( checkI == DbStruct.INSERT_ERR) tableActivityInterFace.addTableErr("add new Table fail");
            else tableActivityInterFace.addTableSuccess("add new Table Successful", tableDAO.getByRoomID(roomID));
        }
        else {
            tableActivityInterFace.addTableErr("already exists: "+table.getName());
        }
    }
}
