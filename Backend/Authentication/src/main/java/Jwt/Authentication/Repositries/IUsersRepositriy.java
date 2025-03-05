package Jwt.Authentication.Repositries;


import Jwt.Authentication.Generic.mGeneric;
import Jwt.Authentication.Models.Auth.mJwtData;
import Jwt.Authentication.Models.Auth.mLogin;
import Jwt.Authentication.Models.Auth.mSignUp;
import Jwt.Authentication.Models.Users.mUsers;

import java.util.List;

public interface IUsersRepositriy {
    mGeneric.mApiResponse signUp(mSignUp.SignUp signUp);
    mGeneric.mApiResponse signIn(mLogin login);
    mUsers.GetUserForAuthentication loadUserDetailsByName(String email);
    mJwtData getUserDetailsForJwtToken(String email);
}
