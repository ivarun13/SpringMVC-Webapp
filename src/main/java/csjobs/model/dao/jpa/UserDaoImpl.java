package csjobs.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import csjobs.model.Application;
import csjobs.model.Job;
import csjobs.model.User;
import csjobs.model.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUser( Long id )
    {
        return entityManager.find( User.class, id );
    }

    @Override
    public User getUser( String email )
    {
        String query = "from User user left join fetch user.roles "
            + "where lower(email) = :email";

        List<User> users = entityManager.createQuery( query, User.class )
            .setParameter( "email", email.toLowerCase() )
            .getResultList();
        return users.size() == 0 ? null : users.get( 0 );
    }

    @Override
    public List<User> getUsers( String role )
    {
        String query = "select user from User user left join user.roles role "
            + "where role = :role";

        return entityManager.createQuery( query, User.class )
            .setParameter( "role", role )
            .getResultList();
    }

    @Override
    @Transactional
    public User saveUser( User user )
    {
        return entityManager.merge( user );
    }
    
    @Override
	public List<Application> getApplications(User user) {
		return entityManager.createQuery( "from Application WHERE applicant_id = "+user.getId(), Application.class )
	            .getResultList();
	}

	@Override
	public List<Application> getApplications(Job job) {
		// TODO Auto-generated method stub
		return entityManager.createQuery( "from Application WHERE job_id = "+job.getId(), Application.class )
	            .getResultList();
	}

}
