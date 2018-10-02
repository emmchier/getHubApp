package com.example.emmchierchie.gethubapp.View;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.emmchierchie.gethubapp.Controller.ReposController;
import com.example.emmchierchie.gethubapp.Model.POJO.Repository;
import com.example.emmchierchie.gethubapp.Model.ResultListener;
import com.example.emmchierchie.gethubapp.R;
import com.example.emmchierchie.gethubapp.View.Adapters.AdapterRecyclerViewRepos;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityHome extends AppCompatActivity implements AdapterRecyclerViewRepos.ReposListener {

    // creo una constante para pedir la cantidad deseada de resultados por página de búsqueda
    private static final int PER_PAGE = 50;

    @BindView(R.id.editTextQuery)
    EditText editTextQuery;

    @BindView(R.id.imageButtonSearch)
    ImageButton imageButtonSearch;

    @BindView(R.id.recyclerViewRepos)
    RecyclerView recyclerViewRepos;

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;

    private AdapterRecyclerViewRepos adapterRecyclerViewRepos;
    private String request;
    private ReposController reposController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        // toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        adapterRecyclerViewRepos = new AdapterRecyclerViewRepos(this);

        // seteo buscador
        imageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResultListener<List<Repository>> viewListener = new ResultListener<List<Repository>>() {
                    @Override
                    public void finish(List<Repository> results) {

                        // si hay resultados, conservo el string de búsqueda en la data base
                        if(results.size() > 0){
                            keepRequestString(request);
                            //Toast.makeText( ActivityHome.this, String.valueOf(results.size()), Toast.LENGTH_SHORT ).show();
                            adapterRecyclerViewRepos.updateUserList( results );
                        } else {
                            Toast.makeText( ActivityHome.this, "Results not found. Try again", Toast.LENGTH_SHORT ).show();
                        }
                    }
                };
                request = editTextQuery.getText().toString();
                // harcodeo la url para realizar una consulta por el buscador teniendo en cuenta la clasificación por estrellas
                // en orden descendente hasta 50 resultados
                reposController.getResults(viewListener,request,"stars", "desc", PER_PAGE);
            }
        } );

        // recyclerview

        recyclerViewRepos.setAdapter(adapterRecyclerViewRepos);

        // lista de repos en LinearLayout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewRepos.setLayoutManager(layoutManager);
        recyclerViewRepos.setHasFixedSize(true);

    }

    // guardo el string de la última búsqueda para que figure en barra del buscador
    private void keepRequestString (String request){
        SharedPreferences preferences = getPreferences( MODE_PRIVATE );
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString( "lastRequest", request );
        editor.commit();
    }

    // creo un controlador que al iniciar la app, crea una data base en room y obtiene la última busqueda
    @Override
    protected void onStart() {
        super.onStart();
        reposController = new ReposController(this);
        getLastSearch();
    }

    // le pido al controlador que al detenerse la app, cierre la data base
    @Override
    protected void onStop() {
        super.onStop();
        reposController.closeDb();
    }

    // método de consulta
    private void getLastSearch() {
        SharedPreferences preferences = getPreferences( MODE_PRIVATE );
        String lastRequest = preferences.getString( "lastRequest" , "noResults");
        if (!lastRequest.equals( "noResults" )){
            editTextQuery.setText( lastRequest );
            //pido la lista anterior cargada en room y se la seteo al recycler
            List<Repository> listRepositoryDB = reposController.getRespositoryDB();
            //Toast.makeText( ActivityHome.this, String.valueOf(listRepositoryDB.size()), Toast.LENGTH_SHORT ).show();
            adapterRecyclerViewRepos.updateUserList( listRepositoryDB );
        }
    }

    // hacemos click en la celda del repo y nos manda al detalle
    @Override
    public void showDetail(Repository repository) {
        Intent intent = new Intent(this, ActivityRepoDetail.class);
        Bundle bundle = new Bundle();
        bundle.putString(ActivityRepoDetail.NAME, repository.getName());
        bundle.putString(ActivityRepoDetail.DESCRIPTION, repository.getDescription());
        bundle.putInt(ActivityRepoDetail.STARGAZERS_COUNT, repository.getStargazers_count());
        bundle.putInt(ActivityRepoDetail.FORKS_COUNT, repository.getForks_count());
        intent.putExtras(bundle );
        startActivity(intent);
    }
}
