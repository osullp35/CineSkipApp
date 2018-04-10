package com.example.admin.cineskip;

/**
 * Created by Admin on 20/03/2018.
 */

public class MovieTimes {
    private String Name;
    private String Time2;
    private String Time3;

    public MovieTimes() {
    }

    public MovieTimes(String name, String time2, String time3) {
        Name = name;
        Time2 = time2;
        Time3 = time3;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTime2() {
        return Time2;
    }

    public void setTime2(String time2) {
        Time2 = time2;
    }

    public String getTime3() {
        return Time3;
    }

    public void setTime3(String time3) {
        Time3 = time3;
    }
}
