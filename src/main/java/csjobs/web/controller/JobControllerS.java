package csjobs.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;

import csjobs.model.Job;
import csjobs.model.User;
import csjobs.model.dao.JobDao;
import csjobs.model.dao.UserDao;
import csjobs.web.editor.UserPropertyEditor;

@Controller
@SessionAttributes("job")
public class JobControllerS {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JobDao jobDao;

    @Autowired
    private WebApplicationContext context;

    @InitBinder
    public void initBinder( WebDataBinder binder )
    {
        binder.registerCustomEditor( User.class,
            (UserPropertyEditor) context.getBean( "userPropertyEditor" ) );
        binder.registerCustomEditor( Date.class,
            new CustomDateEditor( new SimpleDateFormat( "M/d/yyyy" ), true ) );
    }

    @RequestMapping(value = "/job/create.html", method = RequestMethod.GET)
    public String create( ModelMap models )
    {
        models.put( "job", new Job() );
        models.put( "reviewers", userDao.getUsers( "ROLE_REVIEWER" ) );
        return "job/create";
    }

    @RequestMapping(value = "/job/create.html", method = RequestMethod.POST)
    public String create( @ModelAttribute Job job, SessionStatus sessionStatus )
    {
    	
        List<User> Rjob = new ArrayList<>();
    	
    	for( User u : job.getCommitteeMembers())
    	{
    		Rjob.add(u);
    	}
        
        if(!Rjob.contains(job.getCommitteeChair()))
        {
        	Rjob.add(job.getCommitteeChair());
        }
    	
        
    	//job.setCommitteeChair(userDao.getUser(chair_id));
    	job.setCommitteeMembers(Rjob);
        job = jobDao.saveJob( job );
        sessionStatus.setComplete();

        return "redirect:/admin.html";
    }

    @RequestMapping(value = "/job/edit.html", method = RequestMethod.GET)
    public String edit( @RequestParam Long id, ModelMap models )
    {
        models.put( "job", jobDao.getJob( id ) );
        models.put( "reviewers", userDao.getUsers( "ROLE_REVIEWER" ) );
        return "job/edit";
    }

    @RequestMapping(value = "/job/edit.html", method = RequestMethod.POST)
    public String edit( @ModelAttribute Job job, SessionStatus sessionStatus )
    {
    	
        List<User> Rjob = new ArrayList<>();
    	
    	for( User u : job.getCommitteeMembers())
    	{
    		Rjob.add(u);
    	}
        
        if(!Rjob.contains(job.getCommitteeChair()))
        {
        	Rjob.add(job.getCommitteeChair());
        }
    	
        
    	//job.setCommitteeChair(userDao.getUser(chair_id));
    	job.setCommitteeMembers(Rjob);
        job = jobDao.saveJob( job );
        sessionStatus.setComplete();

        return "redirect:/admin.html";
    }

}
