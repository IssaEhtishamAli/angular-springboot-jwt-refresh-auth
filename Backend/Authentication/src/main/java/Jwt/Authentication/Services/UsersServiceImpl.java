package Jwt.Authentication.Services;


import Jwt.Authentication.Generic.mGeneric;
import Jwt.Authentication.Models.Auth.mJwtData;
import Jwt.Authentication.Models.Auth.mLogin;
import Jwt.Authentication.Models.Auth.mSignUp;
import Jwt.Authentication.Models.Users.mUsers;
import Jwt.Authentication.Repositries.IUsersRepositriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements IUsersService{
    @Autowired
    private IUsersRepositriy _usersRepositriy;
    @Override
    public mGeneric.mApiResponse signUp(mSignUp.SignUp signUp) {
        return _usersRepositriy.signUp(signUp);
    }

    @Override
    public mGeneric.mApiResponse signIn(mLogin login) {
        return _usersRepositriy.signIn(login);
    }
    @Override
    public mJwtData getUserDetailsForJwtToken(String email) {
        return _usersRepositriy.getUserDetailsForJwtToken(email);
    }
}
