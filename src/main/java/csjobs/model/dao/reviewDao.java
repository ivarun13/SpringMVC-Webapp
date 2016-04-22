package csjobs.model.dao;

import java.util.List;

import csjobs.model.Application;
import csjobs.model.Review;
import csjobs.model.Round;

public interface reviewDao {

	
	Review saveReview( Review review );

	List<Round> getRounds(Application application);
}
