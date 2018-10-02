package com.example.emmchierchie.gethubapp.Model.POJO;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ReposContainer {
    @SerializedName( "items" )
    List<Repository> list;

    public ReposContainer(List<Repository> list) {
        this.list = list;
    }

    public List<Repository> getList() {
        return list;
    }

    public void setList(List<Repository> list) {
        this.list = list;
    }
}
