package com.example.emmchierchie.gethubapp.Model.ROOM;
import android.arch.persistence.room.Room;
import android.content.Context;
import com.example.emmchierchie.gethubapp.Model.POJO.Repository;
import java.util.List;

public class DataBase {

    private RoomAppDatabase db;

    // creo la data base de room
    public DataBase(Context context){
        db = Room.databaseBuilder(context,
                RoomAppDatabase.class, "database-name").allowMainThreadQueries().build();
    }

    // cierro data base
    public void closeDB(){
        db.close();
    }

    // obtengo la lista de repos de room
    public List<Repository> getRepositorys(){
        return db.daoRoomRepository().getRepositorys();
    }

    // agrego todos la lista de repos a room
    public void addAllRepositorys(List<Repository> list){
        db.daoRoomRepository().insetAll( list );
    }

    // borro la data base
    public void deleteDB (){
        db.daoRoomRepository().delete();
    }

}
