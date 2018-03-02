package com.example.haseeb.loginapp.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;


@Entity(indices = {@Index(value = "username", unique = true)})
public class User implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "age")
    private String age;

    public String name;
    @ColumnInfo(name = "city_name")
    public String cityName;
public User(){}

    protected User(Parcel in) {
        uid = in.readInt();
        username = in.readString();
        password = in.readString();
        age = in.readString();
        name = in.readString();
        cityName = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(uid);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(age);
        dest.writeString(name);
        dest.writeString(cityName);
    }
}

