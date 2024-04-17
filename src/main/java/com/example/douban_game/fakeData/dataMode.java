package com.example.douban_game.fakeData;

public class dataMode {

    public double rating;

    public String kind;

    //public Date publish_date 随机生成时间
    public String publish_date;

    public String location;

    public String language;

    public int commentNum;

    public String l_kind;

    public String getL_kind() {
        return l_kind;
    }

    public void setL_kind(String l_kind) {
        this.l_kind = l_kind;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "dataMode{" +
                "rating=" + rating +
                ", kind='" + kind + '\'' +
                ", publish_date='" + publish_date + '\'' +
                ", location='" + location + '\'' +
                ", language='" + language + '\'' +
                ", commentNum=" + commentNum +
                ", l_kind='" + l_kind + '\'' +
                '}';
    }
}
