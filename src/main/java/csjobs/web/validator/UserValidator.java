package csjobs.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import csjobs.model.User;
import csjobs.model.dao.UserDao;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean supports( Class<?> clazz )
    {
        return User.class.isAssignableFrom( clazz );
    }

    @Override
    public void validate( Object target, Errors errors )
    {
        ValidationUtils.rejectIfEmptyOrWhitespace( errors, "email",
            "error.field.empty" );
        ValidationUtils.rejectIfEmptyOrWhitespace( errors, "password",
            "error.field.empty" );
        ValidationUtils.rejectIfEmptyOrWhitespace( errors, "password2",
            "error.field.empty" );
        ValidationUtils.rejectIfEmptyOrWhitespace( errors, "lastName",
            "error.field.empty" );
        ValidationUtils.rejectIfEmptyOrWhitespace( errors, "firstName",
            "error.field.empty" );
        ValidationUtils.rejectIfEmptyOrWhitespace( errors, "phone",
            "error.field.empty" );
        ValidationUtils.rejectIfEmptyOrWhitespace( errors, "address",
            "error.field.empty" );

        User user = (User) target;

        String email = user.getEmail();
        if( email != null && userDao.getUser( email ) != null )
            errors.rejectValue( "email", "error.email.exists" );

        if( user.getPassword() != null
            && !user.getPassword().equals( user.getPassword2() ) )
            errors.rejectValue( "password2", "error.password.nomatch" );
    }

}
