package csjobs.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import csjobs.model.Job;
import csjobs.model.User;

@Test(groups = "JobDaoTest")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JobDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private JobDao jobDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void getJobs()
    {
        assert jobDao.getJobs().size() == 2;
    }

    @Test(dependsOnGroups = "UserDaoTest", dependsOnMethods = "getJobs")
    public void countCommittees()
    {
        int committees = 0;

        User reviewer3 = userDao.getUser( "reviewer3@localhost.localdomain" );
        List<Job> jobs = jobDao.getJobs();
        for( Job job : jobs )
            if( job.getCommitteeMembers().contains( reviewer3 ) ) ++committees;

        assert committees == 2;
    }

}
