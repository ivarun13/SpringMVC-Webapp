package csjobs.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import csjobs.model.Application;
import csjobs.model.File;
import csjobs.model.Job;
import csjobs.model.User;
import csjobs.model.dao.ApplicationDao;
import csjobs.model.dao.FileDao;
import csjobs.model.dao.JobDao;
import csjobs.model.dao.UserDao;
import csjobs.security.SecurityUtils;
import csjobs.web.validator.UserValidator;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JobDao jobDao;
    
    @Autowired
    private FileDao fileDao;

    @Autowired
    private ApplicationDao applicationDao;
    
    @Autowired
    private UserValidator userValidator;

    private static final Logger logger = LoggerFactory
        .getLogger( UserController.class );

    @RequestMapping(value = "/register.html", method = RequestMethod.GET)
    public String register( ModelMap models )
    {
        models.put( "user", new User() );
        return "register";
    }

    @RequestMapping(value = "/register.html", method = RequestMethod.POST)
    public String register( @ModelAttribute User user,
        BindingResult bindingResult, SessionStatus sessionStatus )
    {
        userValidator.validate( user, bindingResult );
        if( bindingResult.hasErrors() ) return "register";

        user = userDao.saveUser( user );
        sessionStatus.setComplete();

        logger.info( "Registered new user " + user.getEmail() );

        return "redirect:/";
    }

    @RequestMapping("/login.html")
    public String login()
    {
        return "login";
    }

    @RequestMapping("/admin.html")
    public String admin( ModelMap models )
    {
        models.put( "jobs", jobDao.getJobs() );
        return "admin";
    }

    @RequestMapping("/reviewer.html")
    public String reviewer(ModelMap models,@RequestParam(required = false) String term)
    {
    	if( StringUtils.hasText( term ) )
    	{
            models.addAttribute( "files", fileDao.searchFiles( term, -1 ) );
            models.addAttribute("term",term);
            
            
    	//List<Job> reviewer_job = new ArrayList<>();
    	//models.put("application", applicationDao.getApp(file));
    	   
    	}
    	
    	/*for(Job job : jobDao.getOpenJobs())
    	{
    		if(job.getCommitteeMembers().contains(userDao.getUser(SecurityUtils.getUser().getId())))
    		{
    			reviewer_job.add(job);
    		}
    	}
    	System.out.println(reviewer_job.size());
    	List<Job> chair_job = new ArrayList<>();
    	for(Job j : jobDao.getOpenJobs())
    	{
    		if(j.getCommitteeChair().getId().equals(SecurityUtils.getUser().getId()))
    		{
    			chair_job.add(j);
    		}
    	}
    	//System.out.println(user.getId());
    	models.put("chair_job", chair_job);
    	models.put("reviewer_job", reviewer_job);*/
        return "reviewer";
    }

    @RequestMapping("/applicant.html")
    public String applicant( ModelMap models )
    {
        // The User object in the session is a "detached" object, which means
        // it may not be update-to-date, and it cannot be used to load other
        // objects. So here we just use the id to retrieve another copy of the
        // user from the database.
        User user = userDao.getUser( SecurityUtils.getUser().getId() );

        Set<Job> appliedJobs = new HashSet<Job>();
        for( Application application : user.getApplications() )
            appliedJobs.add( application.getJob() );

        List<Job> availableJobs = jobDao.getOpenJobs();
        Iterator<Job> iterator = availableJobs.iterator();
        while( iterator.hasNext() )
            if( appliedJobs.contains( iterator.next() ) ) iterator.remove();

        models.put( "user", user );
        models.put( "availableJobs", availableJobs );

        return "applicant";
    }
    
    @RequestMapping("/reviewerApp.html")
    public String reviewerApp(ModelMap models,@RequestParam(required=false) Long id)
    {
    	Job job = jobDao.getJob2(id);
    	
    	List<Application> apps = userDao.getApplications(job);
    	List<Application> apps2 = new ArrayList<>();
    	List<Application> apps3 = new ArrayList<>();
    	
    	
    	for(Application a : userDao.getApplications(job))
    	{
    		
    		if(a.getRounds().get(0).isPassed())
    		{
    			apps2.add(a);
    			apps.remove(a);
    		}
    	}
    	
    	for(Application a : userDao.getApplications(job))
    	{
    		
    		if(a.getRounds().get(1).isPassed())
    		{
    			apps3.add(a);
    			apps2.remove(a);
    		}
    	}
    	
    	models.put("apps", apps);
    	models.put("apps2", apps2);
    	models.put("apps3", apps3);
    	
        return "reviewerApp";
    }
    
    @RequestMapping(value="/chairApp.html",method = RequestMethod.GET)
    public String chairApp(ModelMap models,@RequestParam(required=false) Long id)
    {
    	Job job = jobDao.getJob2(id);
    	
    	List<User> reviewers = job.getCommitteeMembers();
    	List<Application> apps = userDao.getApplications(job);
    	List<Application> apps2 = new ArrayList<>();
    	List<Application> apps3 = new ArrayList<>();
    	for(Application a : userDao.getApplications(job))
    	{
    		if(a.getRounds().get(0).isPassed())
    		{
    			apps2.add(a);
    			apps.remove(a);
    		}
    	}
    	
    	for(Application a : userDao.getApplications(job))
    	{
    		
    		if(a.getRounds().get(1).isPassed())
    		{
    			apps3.add(a);
    			apps2.remove(a);
    		}
    	}
    	System.out.println("test" + apps.size());
    	models.put("reviewers", reviewers);
    	models.put("apps", apps);
        models.put("apps2", apps2);
        models.put("apps3", apps3);
        return "chairApp";
    }

    @RequestMapping(value="/chairApp.html",method = RequestMethod.POST)
    public String chairApp(@RequestParam(value = "applications",required = false) Long[] applications,@RequestParam(value = "applications2",required = false) Long[] applications2,@RequestParam(value = "applications3",required = false) Long[] applications3,ModelMap models)
    {
    	List<Application> a = new ArrayList<>();
    	if(applications != null)
    	{
    	for(Long id : applications)
    	{
    		a.add(applicationDao.getApplication(id));
    		applicationDao.getApplication(id).getRounds().get(0).setPassed(true);
    		System.out.println(applicationDao.getApplication(id).getRounds().get(0).getId());
    		applicationDao.saveApplication(applicationDao.getApplication(id));
    	}
    	}
    	
    	if(applications2 != null)
    	{
    	for(Long id : applications2)
    	{
    		a.add(applicationDao.getApplication(id));
    		applicationDao.getApplication(id).getRounds().get(1).setPassed(true);
    		//System.out.println(applicationDao.getApplication(id).getRounds().get(0).getId());
    		applicationDao.saveApplication(applicationDao.getApplication(id));
    	}
    	}
    	
    	if(applications3 != null)
    	{
    	for(Long id : applications3)
    	{
    		a.add(applicationDao.getApplication(id));
    		applicationDao.getApplication(id).getRounds().get(2).setPassed(true);
    		//System.out.println(applicationDao.getApplication(id).getRounds().get(0).getId());
    		applicationDao.saveApplication(applicationDao.getApplication(id));
    	}
    	}
    	
    	models.put("review2", a);
        return "redirect:/reviewer.html";
    }


}
