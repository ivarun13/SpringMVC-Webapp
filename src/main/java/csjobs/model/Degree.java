package csjobs.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Degree implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String school;

    private Integer year;

    public Degree()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getSchool()
    {
        return school;
    }

    public void setSchool( String school )
    {
        this.school = school;
    }

    public Integer getYear()
    {
        return year;
    }

    public void setYear( Integer year )
    {
        this.year = year;
    }

}
