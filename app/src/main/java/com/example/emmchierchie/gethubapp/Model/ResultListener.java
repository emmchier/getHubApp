package com.example.emmchierchie.gethubapp.Model;

// escuchador general de resultados
public interface ResultListener<T> {
    void finish(T results);
}
