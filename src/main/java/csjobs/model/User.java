package csjobs.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean enabled;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String address;

    private String phone;

    @OneToMany(mappedBy = "applicant")
    @OrderBy("id desc")
    private List<Application> applications;

    /**
     * There are two special roles in the system: ROLE_ADMIN and ROLE_REVIEWER.
     * Anyone who doesn't have either role is a regular user, i.e. applicant.
     */
    @ElementCollection
    @CollectionTable(name = "authorities",
        joinColumns = @JoinColumn(name = "user_id") )
    @Column(name = "role")
    private Set<String> roles;

    /**
     * This field is used for password confirmation during registration - it's
     * not stored in the database.
     */
    @Transient
    private String password2;

    public User()
    {
        enabled = true;
        roles = new HashSet<String>();
    }

    public boolean isAdmin()
    {
        return roles.contains( "ROLE_ADMIN" );
    }

    public boolean isReviewer()
    {
        return roles.contains( "ROLE_REVIEWER" );
    }

    public String getName()
    {
        return firstName + " " + lastName;
    }

    @Override
    public String getUsername()
    {
        return email;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities()
    {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for( String role : roles )
            authorities.add( new SimpleGrantedAuthority( role ) );
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled( boolean enabled )
    {
        this.enabled = enabled;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress( String address )
    {
        this.address = address;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone( String phone )
    {
        this.phone = phone;
    }

    public List<Application> getApplications()
    {
        return applications;
    }

    public void setApplications( List<Application> applications )
    {
        this.applications = applications;
    }

    public Set<String> getRoles()
    {
        return roles;
    }

    public void setRoles( Set<String> roles )
    {
        this.roles = roles;
    }

    public String getPassword2()
    {
        return password2;
    }

    public void setPassword2( String password2 )
    {
        this.password2 = password2;
    }

}
