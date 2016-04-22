package csjobs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import csjobs.model.dao.ApplicationDao;

@Controller
public class ApplicationController {

    @Autowired
    private ApplicationDao applicationDao;

    @RequestMapping("/application/view.html")
    public String view( @RequestParam Long id, ModelMap models )
    {
        models.put( "application", applicationDao.getApplication( id ) );
        return "application/view";
    }

}
