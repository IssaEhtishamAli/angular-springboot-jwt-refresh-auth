package Jwt.Authentication.Services;

import Jwt.Authentication.Models.Users.mUsers;

import java.util.List;

public interface IAdminService {
    int getTotalUsers();
    List<mUsers.Users> getPaginatedUsers(int limit, int offset);
}
