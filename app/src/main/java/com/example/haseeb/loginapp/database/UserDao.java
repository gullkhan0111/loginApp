package com.example.haseeb.loginapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.haseeb.loginapp.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Insert
    void insertAll(User... users);

    @Query("DELETE FROM user WHERE uid = :noteId")
    int delete(final int noteId);


    @Query("SELECT * FROM user WHERE username LIKE :username AND password LIKE :password LIMIT 1")
    List<User> Authentication(String username, String password);


    @Query("SELECT * FROM user WHERE city_name LIKE :city_name")
    List<User> findCityName(String city_name);

    @Query("SELECT * FROM user WHERE age LIKE :Age")
    List<User> findByAge(String Age);


    @Query("SELECT * FROM user WHERE username LIKE :UserName")
    List<User> findByUserName(String UserName);

    @Query("SELECT * FROM user WHERE username LIKE :userName OR city_name LIKE :cityName OR age LIKE :age")
    List<User> findUsers(String userName, String cityName, String age);


    @Query("SELECT city_name FROM user WHERE username LIKE :UserName ")
    String findCity (String UserName);


    @Query("SELECT age FROM user WHERE username LIKE :UserName ")
    String findAge(String UserName);






}
