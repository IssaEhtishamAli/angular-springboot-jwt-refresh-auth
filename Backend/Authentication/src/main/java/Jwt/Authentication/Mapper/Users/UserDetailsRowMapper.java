package Jwt.Authentication.Mapper.Users;

import Jwt.Authentication.Models.Users.mUsers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailsRowMapper implements RowMapper<mUsers.GetUserForAuthentication> {
    @Override
    public mUsers.GetUserForAuthentication mapRow(ResultSet rs, int rowNum) throws SQLException {
        mUsers.GetUserForAuthentication user= new mUsers.GetUserForAuthentication();
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setUserName(rs.getString("name"));
        return user;
    }
}
