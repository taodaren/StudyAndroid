package io.github.taodaren.studymvp.model;

/**
 * 实体类
 */

public class MainModelBean {
    private String city;
    private String wd;
    private String ws;
    private String time;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MainModelBean{" +
                "city='" + city + '\'' +
                ", wd='" + wd + '\'' +
                ", ws='" + ws + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
