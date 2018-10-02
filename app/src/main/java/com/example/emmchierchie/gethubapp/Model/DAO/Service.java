package com.example.emmchierchie.gethubapp.Model.DAO;
import com.example.emmchierchie.gethubapp.Model.POJO.ReposContainer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    //armo la url con la "consulta" como variable que pediré en el buscador, la clasificación de estrellas, el orden de los resultados
    //que por defecto es descendente y la cantidad de resultados por página
    @GET("/search/repositories?")
    Call<ReposContainer> getRepos(@Query("q") String request,
                                  @Query("sort") String sort,
                                  @Query("order") String order,
                                  @Query("per_page") Integer perPage);
}

