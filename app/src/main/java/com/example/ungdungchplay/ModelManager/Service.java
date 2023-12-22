package com.example.ungdungchplay.ModelManager;

public class Service {
//    public static final String CREATE_TABLE_SERVICE = "" +
//            "create table service (" +
//            "serviceID INTEGER PRIMARY KEY AUTOINCREMENT ," +
//            "name TEXT," +
//            "type INTEGER," +
//            "count INTEGER," +
//            "price INTEGER," +
//            "image INTEGER," +
//            "description TEXT)";
    private int id,price,count,type;
    private String name,description,imageURi;

    public Service(int id, int price, int count, int type, String name, String description, String imageURi) {
        this.id = id;
        this.price = price;
        this.count = count;
        this.type = type;
        this.name = name;
        this.description = description;
        this.imageURi = imageURi;
    }

    public Service() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String  toString() {
        return "Service{" +
                "id=" + id +
                ", price=" + price +
                ", count=" + count +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageURi='" + imageURi + '\'' +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURi() {
        return imageURi;
    }

    public void setImageURi(String imageURi) {
        this.imageURi = imageURi;
    }
}
