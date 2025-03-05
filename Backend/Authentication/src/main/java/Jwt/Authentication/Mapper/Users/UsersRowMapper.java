package Jwt.Authentication.Mapper.Users;

import Jwt.Authentication.Models.Users.mUsers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRowMapper implements RowMapper<mUsers.Users> {
    @Override
    public mUsers.Users mapRow(ResultSet rs, int rowNum)throws SQLException{
        mUsers.Users users= new mUsers.Users();
        users.setId(rs.getInt("id"));
        users.setName(rs.getString("name"));
        users.setEmail(rs.getString("email"));
        users.setAddress(rs.getString("address"));
        users.setPassword(rs.getString("password"));
        users.setCnic(rs.getString("cnic"));
        users.setGender(rs.getString("gender"));
        users.setDob(rs.getString("dob"));
        return  users;
    }
}
