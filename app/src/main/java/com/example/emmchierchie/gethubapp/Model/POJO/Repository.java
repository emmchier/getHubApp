package com.example.emmchierchie.gethubapp.Model.POJO;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

// creo la tabla para data base de Repos
@Entity
public class Repository {
    // primary key que sea autoincrementable
    @PrimaryKey (autoGenerate = true)
    @NonNull
    private Integer id;
    private String name;
    private String description;
    private Integer stargazers_count;
    private Integer forks_count;

    public Repository(Integer id, String name, String description, Integer stargazers_count, Integer forks_count) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stargazers_count = stargazers_count;
        this.forks_count = forks_count;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(Integer stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public Integer getForks_count() {
        return forks_count;
    }

    public void setForks_count(Integer forks_count) {
        this.forks_count = forks_count;
    }
}
