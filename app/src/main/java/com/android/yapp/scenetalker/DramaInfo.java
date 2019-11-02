package com.android.yapp.scenetalker;

public class DramaInfo {
    //int id;//db
    String picture;//포스터경로
    String production;//제작사
    String dname;//드라마이름
    String ratingtext;//평균시청률 글자
    String rating; //시청률 숫자
    String bar;// |
    String time; //토일오후9시
    String gotofeed; //피드보러가기 버튼 텍스트
    String count; //오른쪽 위 아이템 개수

    public DramaInfo(String picture, String production, String dname, String ratingtext, String rating, String bar, String time, String gotofeed, String count) {
        this.picture = picture;
        this.production = production;
        this.dname = dname;
        this.ratingtext = ratingtext;
        this.rating = rating;
        this.bar = bar;
        this.time = time;
        this.gotofeed = gotofeed;
        this.count = count;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getRatingtext() {
        return ratingtext;
    }

    public void setRatingtext(String ratingtext) {
        this.ratingtext = ratingtext;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGotofeed() {
        return gotofeed;
    }

    public void setGotofeed(String gotofeed) {
        this.gotofeed = gotofeed;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
