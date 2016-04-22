package csjobs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import csjobs.model.dao.JobDao;

@Controller
public class SearchController {

	@Autowired
	private JobDao jobDao;
	
	@RequestMapping("/search.html")
    public String search( @RequestParam(required = false) String term,
        ModelMap models )
    {
        if( StringUtils.hasText( term ) )
            models.addAttribute( "jobs", jobDao.searchJobs( term, -1 ) );
        return "search";
    }
}
