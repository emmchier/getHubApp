package com.example.emmchierchie.gethubapp.Model.ROOM;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.example.emmchierchie.gethubapp.Model.POJO.Repository;

// creo las tablas en room + el método abstracto con el que haré las consultas por tabla
@Database(entities = {Repository.class}, version = 1)
public abstract class RoomAppDatabase extends RoomDatabase {
    public abstract DAORoomRepository daoRoomRepository();
}
