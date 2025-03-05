package Jwt.Authentication.Utill;

import Jwt.Authentication.Models.Users.mUsers;
import Jwt.Authentication.Repositries.IUsersRepositriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private IUsersRepositriy _userRepositriy;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            mUsers.GetUserForAuthentication user = _userRepositriy.loadUserDetailsByName(email);
            if(user !=null){
                return new User(user.getUserName(),user.getPassword(),new ArrayList<>());
            }
            else {
                throw new UsernameNotFoundException("User Name Not Found !!");
            }

        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Exception: " + ex.getMessage());
            throw ex;
        }
    }
}
