package org.hs.os.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "feed_table")
public class Feed {

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "count")
    private int count;

    private Date erstelltAm;

    private Date suchDauer;

    private Date suchIntervall;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<TwitterStatus> twitterStatus;

    public Feed() {
    }

    public Feed(String keyword, int count, Date erstelltAm, Date suchDauer, Date suchIntervall, List<TwitterStatus> twitterStatus) {
        this.keyword = keyword;
        this.count = count;
        this.erstelltAm = erstelltAm;
        this.suchDauer = suchDauer;
        this.suchIntervall = suchIntervall;
        this.twitterStatus = twitterStatus;
    }

    public String getKeyword() {
        return keyword;
    }

    public int getCount() {
        return count;
    }

    public long getId() {
        return id;
    }

    public List<TwitterStatus> getTwitterStatus() {
        return twitterStatus;
    }

    public void setTwitterStatus(List<TwitterStatus> twitterStatus) {
        this.twitterStatus = twitterStatus;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getErstelltAm() {
        return erstelltAm;
    }

    public void setErstelltAm(Date erstelltAm) {
        this.erstelltAm = erstelltAm;
    }

    public Date getSuchDauer() {
        return suchDauer;
    }

    public void setSuchDauer(Date suchDauer) {
        this.suchDauer = suchDauer;
    }

    public Date getSuchIntervall() {
        return suchIntervall;
    }

    public void setSuchIntervall(Date suchIntervall) {
        this.suchIntervall = suchIntervall;
    }
}
