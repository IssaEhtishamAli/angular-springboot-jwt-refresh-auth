package Jwt.Authentication.Mapper.Jwt;



import Jwt.Authentication.Models.Auth.mJwtData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JwtRowMapper implements RowMapper<mJwtData> {
    @Override
    public mJwtData mapRow(ResultSet rs, int rowNum) throws SQLException {
        mJwtData jwtData= new mJwtData();
        jwtData.setEmail(rs.getString("email"));
        jwtData.setFullName(rs.getString("name"));
        jwtData.setPassword(rs.getString("password"));
        jwtData.setUserId(Integer.parseInt(rs.getString("id")));
        jwtData.setProfilePicture(rs.getString("profile_picture"));
        return jwtData;

    }
}
