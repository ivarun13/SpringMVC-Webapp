package csjobs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "applications",
    uniqueConstraints = @UniqueConstraint(
        columnNames = { "job_id", "applicant_id" }) )
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Job job;

    @ManyToOne
    private User applicant;

    @Column(name = "submit_date")
    private Date submitDate;

    @Column(name = "current_job_title")
    private String currentJobTitle;

    @Column(name = "current_job_institution")
    private String currentJobInstitution;

    @Column(name = "current_job_year")
    private Integer currentJobYear;

    @ElementCollection
    @CollectionTable(name = "application_degrees",
        joinColumns = @JoinColumn(name = "application_id") )
    @OrderBy("year desc")
    private List<Degree> degrees;

    @OneToOne
    private File cv;

    @OneToOne
    @JoinColumn(name = "research_statement_id")
    private File researchStatement;

    @OneToOne
    @JoinColumn(name = "teaching_statement_id")
    private File teachingStatement;

    @OneToMany(mappedBy = "application",
        cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @OrderColumn(name = "round_index")
    private List<Round> rounds;

    public Application()
    {
        degrees = new ArrayList<Degree>();
        rounds = new ArrayList<Round>();
        for( int i = 0; i < 3; ++i )
            rounds.add( new Round( this ) );
    }

    public Application( Job job, User applicant )
    {
        this();
        this.job = job;
        this.applicant = applicant;
    }

    public boolean isSubmitted()
    {
        return submitDate != null && submitDate.before( new Date() );
    }

    public boolean isReviewer( User user )
    {
        for( User reviewer : job.getCommitteeMembers() )
            if( reviewer.getId().equals( user.getId() ) ) return true;

        return false;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public Job getJob()
    {
        return job;
    }

    public void setJob( Job job )
    {
        this.job = job;
    }

    public User getApplicant()
    {
        return applicant;
    }

    public void setApplicant( User applicant )
    {
        this.applicant = applicant;
    }

    public Date getSubmitDate()
    {
        return submitDate;
    }

    public void setSubmitDate( Date submitDate )
    {
        this.submitDate = submitDate;
    }

    public String getCurrentJobTitle()
    {
        return currentJobTitle;
    }

    public void setCurrentJobTitle( String currentJobTitle )
    {
        this.currentJobTitle = currentJobTitle;
    }

    public String getCurrentJobInstitution()
    {
        return currentJobInstitution;
    }

    public void setCurrentJobInstitution( String currentJobInstitution )
    {
        this.currentJobInstitution = currentJobInstitution;
    }

    public Integer getCurrentJobYear()
    {
        return currentJobYear;
    }

    public void setCurrentJobYear( Integer currentJobYear )
    {
        this.currentJobYear = currentJobYear;
    }

    public List<Degree> getDegrees()
    {
        return degrees;
    }

    public void setDegrees( List<Degree> degrees )
    {
        this.degrees = degrees;
    }

    public File getCv()
    {
        return cv;
    }

    public void setCv( File cv )
    {
        this.cv = cv;
    }

    public File getResearchStatement()
    {
        return researchStatement;
    }

    public void setResearchStatement( File researchStatement )
    {
        this.researchStatement = researchStatement;
    }

    public File getTeachingStatement()
    {
        return teachingStatement;
    }

    public void setTeachingStatement( File teachingStatement )
    {
        this.teachingStatement = teachingStatement;
    }

    public List<Round> getRounds()
    {
        return rounds;
    }

    public void setRounds( List<Round> rounds )
    {
        this.rounds = rounds;
    }

    public boolean hasUser(User user, Job job)
    {
    	System.out.println(job.getTitle());
    	System.out.println(user.getFirstName());
    	System.out.println(job.getCommitteeMembers().size());
    	
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
