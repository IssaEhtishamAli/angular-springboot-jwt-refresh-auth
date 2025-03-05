package Jwt.Authentication.Repositries;

import Jwt.Authentication.Models.Users.mUsers;

import java.util.List;

public interface IAdminRepositriy {
    int getTotalUsers();
    List<mUsers.Users> getPaginatedUsers(int limit, int offset);
}
