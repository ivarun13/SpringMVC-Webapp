package csjobs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Round round;

    @ManyToOne
    private User reviewer;

    private String comments;

    /**
     * Ranking is usually done only in the last round; for other rounds this
     * field can be empty;
     */
    private Integer rank;

    private Date date;

    public Review()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public Round getRound()
    {
        return round;
    }

    public void setRound( Round round )
    {
        this.round = round;
    }

    public User getReviewer()
    {
        return reviewer;
    }

    public void setReviewer( User reviewer )
    {
        this.reviewer = reviewer;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments( String comments )
    {
        this.comments = comments;
    }

    public Integer getRank()
    {
        return rank;
    }

    public void setRank( Integer rank )
    {
        this.rank = rank;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }

}
