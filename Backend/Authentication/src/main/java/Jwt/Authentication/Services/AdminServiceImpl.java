package Jwt.Authentication.Services;

import Jwt.Authentication.Models.Users.mUsers;
import Jwt.Authentication.Repositries.IAdminRepositriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements IAdminService{
    @Autowired
    private IAdminRepositriy _adminRepositriy;

    @Override
    public List<mUsers.Users> getPaginatedUsers(int limit, int offset){
        return _adminRepositriy.getPaginatedUsers(limit,offset);
    }
    @Override
    public int getTotalUsers(){
        return _adminRepositriy.getTotalUsers();
    }
}
