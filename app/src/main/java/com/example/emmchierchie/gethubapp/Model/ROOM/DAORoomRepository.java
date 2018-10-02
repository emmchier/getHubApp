package com.example.emmchierchie.gethubapp.Model.ROOM;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.example.emmchierchie.gethubapp.Model.POJO.Repository;
import java.util.List;

// creo el dao de Room para guardar repositorios por orden descendente de estrellas
@Dao
public interface DAORoomRepository {
    @Query( "SELECT * FROM Repository ORDER BY stargazers_count DESC" )
    List<Repository> getRepositorys();

    // reemplazo el último listado de búsqueda por el nuevo
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insetAll(List<Repository> lista);

    // borro la lista vieja de la data base
    @Query( "DELETE FROM Repository" )
    void delete();
}
