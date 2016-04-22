package csjobs.model.dao;

import java.util.List;

import csjobs.model.Application;
import csjobs.model.Job;
import csjobs.model.User;

public interface UserDao {

    User getUser( Long id );

    User getUser( String email );

    List<User> getUsers( String role );

    User saveUser( User user );

    
    List<Application> getApplications(User user);
	
	List<Application> getApplications(Job job);
}
