package csjobs.model.dao;

import java.util.List;

import csjobs.model.Job;

public interface JobDao {

    Job getJob( Long id );
    
    Job getJob2( Long id );

    List<Job> getJobs();

    List<Job> getOpenJobs();

    Job saveJob( Job job );
    
    List<Job> searchJobs(String term, int maxResults);

}
