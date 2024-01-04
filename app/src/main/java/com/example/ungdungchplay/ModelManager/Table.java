package com.example.ungdungchplay.ModelManager;

import java.io.Serializable;

public class Table implements Serializable {
    public final static int TABLE_ACTIVE = 1;
    public final static int TABLE_EXISTS = 0;

    private int roomID,status;
    private String name,id;

    public Table() {
    }

    public Table(String id, int roomID, String name, int status) {
        this.id = id;
        this.roomID = roomID;
        this.name = name;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
