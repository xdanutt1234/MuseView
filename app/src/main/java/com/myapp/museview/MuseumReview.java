/**
 * Clasă care reprezintă o recenzie pentru un muzeu.
 */
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

    /**
     * Constructor pentru obiectul MuseumReview cu specificarea numelui de utilizator.
     *
     * @param id       ID-ul recenziei.
     * @param userid   ID-ul utilizatorului care a scris recenzia.
     * @param museumid ID-ul muzeului pentru care s-a scris recenzia.
     * @param rating   Rating-ul acordat muzeului în recenzie.
     * @param comment  Comentariul asociat recenziei.
     * @param username Numele utilizatorului care a scris recenzia.
     */
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
