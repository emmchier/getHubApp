package com.example.emmchierchie.gethubapp.Controller;
import android.content.Context;
import com.example.emmchierchie.gethubapp.Model.DAO.DaoInternet;
import com.example.emmchierchie.gethubapp.Model.POJO.Repository;
import com.example.emmchierchie.gethubapp.Model.ROOM.DataBase;
import com.example.emmchierchie.gethubapp.Model.ResultListener;
import java.util.List;

public class ReposController {
    Context context;
    // instancio data base de room
    DataBase database;

    public ReposController(Context context) {
        this.context = context;
        this.database = new DataBase(context);
    }

    public void getResults(final ResultListener<List<Repository>> viewListener, final String request, String sort, String order, Integer perPage) {
        ResultListener<List<Repository>> controllerListener = new ResultListener<List<Repository>>() {
            @Override
            public void finish(List<Repository> results) {
                // si hay resultados limpio la data base y la recargo con la nueva búsqueda de datos
                if (results.size() > 0){
                    database.deleteDB();
                    database.addAllRepositorys( results );
                }
                viewListener.finish( results );
            }
        };
        // pido al dao de internet los repositorios
        DaoInternet daoInternet = new DaoInternet();
        daoInternet.getRepositories(controllerListener, request, sort, order, perPage);
    }

    // obtengo respos de room
    public List<Repository> getRespositoryDB(){
        return database.getRepositorys();
    }

    // cierro la data base
    public void closeDb(){
        database.closeDB();
    }
}
