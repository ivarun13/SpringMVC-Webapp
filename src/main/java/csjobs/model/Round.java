package csjobs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * Each application needs to go through a number of "rounds", and in each round,
 * the committee members will review the application, and the committee chair
 * will determine whether the application has passed the round.
 */
@Entity
@Table(name = "rounds")
public class Round implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @Column(name = "round_index", nullable = false)
    private int index;

    @OneToMany(mappedBy = "round")
    @OrderBy("id asc")
    private List<Review> reviews;

    private boolean passed;

    public Round()
    {
        reviews = new ArrayList<Review>();
        passed = false;
    }

    public Round( Application application )
    {
        this();
        this.application = application;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public Application getApplication()
    {
        return application;
    }

    public void setApplication( Application application )
    {
        this.application = application;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex( int index )
    {
        this.index = index;
    }

    public List<Review> getReviews()
    {
        return reviews;
    }

    public void setReviews( List<Review> reviews )
    {
        this.reviews = reviews;
    }

    public boolean isPassed()
    {
        return passed;
    }

    public void setPassed( boolean passed )
    {
        this.passed = passed;
    }

}
