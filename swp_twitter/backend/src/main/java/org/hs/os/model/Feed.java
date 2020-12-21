package org.hs.os.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "feed_table")
public class Feed {

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "count")
    private int count;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<TwitterStatus> twitterStatus;

    public Feed() {
    }

    public Feed(String keyword, int count, List<TwitterStatus> twitterStatus) {
        this.keyword = keyword;
        this.count = count;
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
}
