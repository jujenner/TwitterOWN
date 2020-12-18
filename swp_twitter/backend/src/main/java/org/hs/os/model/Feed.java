package org.hs.os.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "feed_table")
public class Feed {

    @Column(name = "keyword")
    private final String keyword;

    @Column(name = "count")
    private final int count;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Feed(String keyword, int count) {
        this.keyword = keyword;
        this.count = count;
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
}
