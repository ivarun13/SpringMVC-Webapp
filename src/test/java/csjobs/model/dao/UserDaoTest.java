package csjobs.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import csjobs.model.Application;
import csjobs.model.User;

@Test(groups = "UserDaoTest")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void getUserById()
    {
        assert userDao.getUser( 1L )
            .getEmail()
            .equalsIgnoreCase( "admin@localhost.localdomain" );
    }

    @Test
    public void getUserByEmail()
    {
        assert userDao.getUser( "reviewer3@localhost.localdomain" ) != null;
        assert userDao.getUser( "applicant1@localhost.localdomain" ) != null;
    }

    @Test(dependsOnMethods = "getUserByEmail")
    public void checkApplication()
    {
        boolean found = false;

        User applicant1 = userDao.getUser( "applicant1@localhost.localdomain" );
        for( Application application : applicant1.getApplications() )
            if( application.getJob().getTitle().equals(
                "Tenure-Track Faculty Position (Start Fall 2015)" ) )
            {
                found = true;
                break;
            }

        assert found;
    }

}
