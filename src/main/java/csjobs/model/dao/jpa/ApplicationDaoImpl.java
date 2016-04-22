package csjobs.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import csjobs.model.Application;
import csjobs.model.File;
import csjobs.model.Job;
import csjobs.model.Round;
import csjobs.model.User;
import csjobs.model.dao.ApplicationDao;

@Repository
public class ApplicationDaoImpl implements ApplicationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @PostAuthorize("returnObject.applicant.id == principal.id or returnObject.isReviewer(principal) or hasRole('ROLE_ADMIN')")
    public Application getApplication( Long id )
    {
        return entityManager.find( Application.class, id );
    }

    @Override
    public Application getApplication( Job job, User applicant )
    {
        String query = "from Application where job = :job and applicant = :applicant";

        List<Application> results = entityManager
            .createQuery( query, Application.class )
            .setParameter( "job", job )
            .setParameter( "applicant", applicant )
            .getResultList();
        return results.size() == 0 ? null : results.get( 0 );
    }

    @Override
    @Transactional
    @PreAuthorize("#application.applicant.id == principal.id or hasRole('ROLE_REVIEWER')")
    public Application saveApplication( Application application )
    {
        return entityManager.merge( application );
    }

    @Override
    public Application getApp( File file )
    {
        String query = "from Application where file = :file";

        List<Application> results = entityManager
            .createQuery( query, Application.class )
            .setParameter( "file", file)
            .getResultList();
        return results.size() == 0 ? null : results.get( 0 );
    }

}
