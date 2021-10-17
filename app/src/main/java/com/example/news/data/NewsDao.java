package com.example.news.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert
    void insertAllNews(List<News> news);


    @Query("DELETE FROM news WHERE category IN(\"General\") ")
    void deleteAllHome();

    @Query("DELETE FROM news WHERE category IN(\"Entertainment\") ")
    void deleteAllEntertainment();

    @Query("DELETE FROM news WHERE category IN(\"Sports\") ")
    void deleteAllSports();

    @Query("DELETE FROM news WHERE category IN(\"Health\") ")
    void deleteAllHealth();

    @Query("DELETE FROM news WHERE category IN(\"Business\") ")
    void deleteAllBusiness();

    @Query("DELETE FROM news WHERE category IN(\"Technology\") ")
    void deleteAllTechnology();


    @Query("SELECT * FROM news WHERE category IN(\"General\")")
    List<News> getAllHome();

    @Query("SELECT * FROM news WHERE category IN(\"Entertainment\")")
    List<News> getAllEntertainment();

    @Query("SELECT * FROM news WHERE category IN(\"Sports\")")
    List<News> getAllSports();

    @Query("SELECT * FROM news WHERE category IN(\"Technology\")")
    List<News> getAllTechnology();

    @Query("SELECT * FROM news WHERE category IN(\"Health\")")
    List<News> getAllHealth();

    @Query("SELECT * FROM news WHERE category IN(\"Business\")")
    List<News> getAllBusiness();


}
