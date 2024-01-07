package com.myapp.museview;

public class MuseumReview {
    private int id;
    private int userid;
    private int museumid;
    private int rating;
    private String comment;
    public String username;

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public int getMuseumid() {
        return museumid;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public MuseumReview(int id, int userid, int museumid, int rating, String comment, String username) {
        this.id = id;
        this.userid = userid;
        this.museumid = museumid;
        this.rating = rating;
        this.comment = comment;
        this.username = username;
    }
    public MuseumReview(int id, int userid, int museumid, int rating, String comment) {
        this.id = id;
        this.userid = userid;
        this.museumid = museumid;
        this.rating = rating;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "MuseumReview{" +
                "id=" + id +
                ", userid=" + userid +
                ", museumid=" + museumid +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
