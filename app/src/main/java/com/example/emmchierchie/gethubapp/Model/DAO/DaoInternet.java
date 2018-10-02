package com.example.emmchierchie.gethubapp.Model.DAO;
import com.example.emmchierchie.gethubapp.Model.POJO.ReposContainer;
import com.example.emmchierchie.gethubapp.Model.POJO.Repository;
import com.example.emmchierchie.gethubapp.Model.ResultListener;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaoInternet {
    private Retrofit retrofit;
    private Service service;

    // peticiones al servidor mediante Retrofit. URL base de github
    public DaoInternet() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory( GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();
        service = retrofit.create(Service.class);
    }

    // pido al controller una lista de repos de github.
    // en la consulta que armé en la interface del service, pido los resultados de consulta con la clasificación
    // en estrellas con orden descendente del que más tiene al que menos
    public void getRepositories(final ResultListener<List<Repository>> controllerListener, String request,String sort,String order, Integer perPage){
        Call<ReposContainer> retrofitListener = service.getRepos(request,sort,order,perPage);
        retrofitListener.enqueue(new Callback<ReposContainer>() {
            @Override
            public void onResponse(Call<ReposContainer> call, Response<ReposContainer> response) {
                ReposContainer reposContainer = response.body();
                controllerListener.finish(reposContainer.getList());
            }

            @Override
            public void onFailure(Call<ReposContainer> call, Throwable t) {
                controllerListener.finish(new ArrayList<Repository>());
            }
        });
    }
}
