package csjobs.model.dao.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import csjobs.model.Job;
import csjobs.model.dao.JobDao;

@Repository
public class JobDaoImpl implements JobDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @PostAuthorize("returnObject.published or hasRole('ROLE_ADMIN')")
    public Job getJob( Long id )
    {
        return entityManager.find( Job.class, id );
    }

    @Override
    public List<Job> getJobs()
    {
        return entityManager
            .createQuery( "from Job order by id desc", Job.class )
            .getResultList();
    }

    @Override
    public List<Job> getOpenJobs()
    {
        String query = "from Job where publishDate < :now "
            + "and (closeDate is null or closeDate > :now) "
            + "order by publishDate asc";

        return entityManager.createQuery( query, Job.class )
            .setParameter( "now", new Date() )
            .getResultList();
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Job saveJob( Job job )
    {
        return entityManager.merge( job );
    }

    @Override
    @PostAuthorize("returnObject.hasReviewer(principal,returnObject) or hasRole('ROLE_ADMIN')")
    public Job getJob2( Long id )
    {
        return entityManager.find( Job.class, id );
    }

	@Override
	public List<Job> searchJobs(String term, int maxResults) {
		// TODO Auto-generated method stub
		TypedQuery<Job> query = entityManager.createNamedQuery("job.search",Job.class);
		if(maxResults > 0) query.setMaxResults(maxResults);
		return query.setParameter("term", term).getResultList();
	}

}
