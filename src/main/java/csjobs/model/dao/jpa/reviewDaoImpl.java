package csjobs.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import csjobs.model.Application;
import csjobs.model.Review;
import csjobs.model.Round;
import csjobs.model.dao.reviewDao;

@Repository
public class reviewDaoImpl implements reviewDao {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	@Transactional
	public Review saveReview(Review review) {
		return entityManager.merge(review);
	}
	
	@Override
	public List<Round> getRounds(Application application) {
		return entityManager.createQuery( "from Round WHERE application_id = "+application.getId(), Round.class )
	            .getResultList();
	}

	
}