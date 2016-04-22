package csjobs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    @Column(name = "publish_date")
    private Date publishDate;

    @Column(name = "close_date")
    private Date closeDate;

    @ManyToOne
    @JoinColumn(name = "committee_chair_id")
    private User committeeChair;

    @ManyToMany
    @JoinTable(name = "job_committee_members",
        joinColumns = @JoinColumn(name = "job_id") ,
        inverseJoinColumns = @JoinColumn(name = "user_id") )
    @OrderBy("lastName asc")
    private List<User> committeeMembers;

    @OneToMany(mappedBy = "job")
    @OrderBy("date asc")
    private List<Application> applications;

    public Job()
    {
        committeeMembers = new ArrayList<User>();
        applications = new ArrayList<Application>();
    }

    public boolean isPublished()
    {
        return publishDate != null && publishDate.before( new Date() );
    }

    public boolean isClosed()
    {
        return closeDate != null && closeDate.before( new Date() );
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public Date getPublishDate()
    {
        return publishDate;
    }

    public void setPublishDate( Date publishDate )
    {
        this.publishDate = publishDate;
    }

    public Date getCloseDate()
    {
        return closeDate;
    }

    public void setCloseDate( Date closeDate )
    {
        this.closeDate = closeDate;
    }

    public User getCommitteeChair()
    {
        return committeeChair;
    }

    public void setCommitteeChair( User committeeChair )
    {
        this.committeeChair = committeeChair;
    }

    public List<User> getCommitteeMembers()
    {
        return committeeMembers;
    }

    public void setCommitteeMembers( List<User> committeeMembers )
    {
        this.committeeMembers = committeeMembers;
    }

    public List<Application> getApplications()
    {
        return applications;
    }

    public void setApplications( List<Application> applications )
    {
        this.applications = applications;
    }
    
    public boolean hasReviewer(User user,Job job)
    {
    	for(User u : job.getCommitteeMembers())
    	{
    		if(u.getId() == user.getId())
    		{
    			return true;
    		}
    	}
		return false;
    }

}
