package Jwt.Authentication.Repositries;


import Jwt.Authentication.Generic.mGeneric;
import Jwt.Authentication.Mapper.Jwt.JwtRowMapper;
import Jwt.Authentication.Mapper.Users.UserDetailsRowMapper;
import Jwt.Authentication.Mapper.Users.UsersRowMapper;
import Jwt.Authentication.Models.Auth.mJwtData;
import Jwt.Authentication.Models.Auth.mLogin;
import Jwt.Authentication.Models.Auth.mSignUp;
import Jwt.Authentication.Models.Users.mUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UsersRepositriyImpl implements IUsersRepositriy {
    @Autowired
    private JdbcTemplate _jdbctemplate;
    @Override
    public mGeneric.mApiResponse signUp(mSignUp.SignUp signUp) {
        try {
            BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
            signUp.setPassword(encoder.encode(signUp.getPassword()));
            String query = "SELECT email FROM users_tbl WHERE email=? AND is_active=true";
            String email;
            try {
                email = _jdbctemplate.queryForObject(query, new Object[]{signUp.getEmail()}, String.class);
                System.out.println(email);
            } catch (EmptyResultDataAccessException e) {
                email = null; // No matching email found
            }
            if(signUp.getId()==0 && email==null){
                KeyHolder keyHolder = new GeneratedKeyHolder();
                String sqlQuery = "INSERT INTO users_tbl (name,email,phone,password,is_active,profile_picture) VALUES (?,?,?,?,?,?) RETURNING id";
                _jdbctemplate.update(connection -> {
                    PreparedStatement pst = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
                    pst.setString(1, signUp.getFullName());
                    pst.setString(2, signUp.getEmail());
                    pst.setString(3, signUp.getPhone());
                    pst.setString(4, signUp.getPassword());
                    pst.setBoolean(5, true);
                    pst.setString(6, signUp.getProfilePicture());
                    return pst;
                }, keyHolder);
                int id=keyHolder.getKey().intValue();
                return  new mGeneric.mApiResponse(1,"Users Registered Successfully!",id);
            }
            else if(email !=null){
                return new mGeneric.mApiResponse<>(0,"This User is Already Registered",null);
            }
            else{
                String sql = "Update users_tbl set name=?,email=?,password=?, phone=?, where id=?";
                _jdbctemplate.update(sql, signUp.getFullName(), signUp.getEmail(),signUp.getPhone(),signUp.getPassword(),signUp.getId());
                return new mGeneric.mApiResponse<>(1,"User Updated Successfully",null);
            }

        } catch (Exception ex) {
            throw ex;
        }
    }
        @Override
        public mGeneric.mApiResponse signIn(mLogin login){
            String sqlQuery = "SELECT COUNT(*) FROM users_tbl WHERE email = ? AND password = ?";
            try {
                int users = _jdbctemplate.queryForObject(sqlQuery, Integer.class,login.getEmail(),login.getPassword());
                if (users > 0) {
                    return new mGeneric.mApiResponse(1,"User Login Successfully",users);
                } else {
                    return new mGeneric.mApiResponse(0,"Invalid User Login and Password",null);
                }
            }catch (Exception ex){
                throw ex;
            }
        }
        @Override
    public mUsers.GetUserForAuthentication loadUserDetailsByName(String email){
            String query="select name,email,password from users_tbl where Lower(email)=Lower('"+email+"') and is_active=true";
            try {
            mUsers.GetUserForAuthentication user = _jdbctemplate.queryForObject(query, new UserDetailsRowMapper());
            return user;
        }catch (Exception ex){
            throw ex;
        }
        }
    @Override
    public mJwtData getUserDetailsForJwtToken(String email) {
        String query="select id,name,email,password,profile_picture from users_tbl where Lower(email)=Lower('"+email+"') and is_active=true";
        try {
            mJwtData jwtData= _jdbctemplate.queryForObject(query, new JwtRowMapper());
            return jwtData;

        }catch (Exception ex){
            throw ex;
        }
    }

}
