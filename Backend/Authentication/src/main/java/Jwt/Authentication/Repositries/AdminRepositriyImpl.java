package Jwt.Authentication.Repositries;

import Jwt.Authentication.Mapper.Users.UsersRowMapper;
import Jwt.Authentication.Models.Users.mUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepositriyImpl implements IAdminRepositriy{
    @Autowired
    private JdbcTemplate _jdbctemplate;
    private static final String COUNT_SQL = "SELECT COUNT(*) FROM users_tbl";
    private static final String PAGINATED_SQL = "SELECT * FROM users_tbl ORDER BY id LIMIT ? OFFSET ?";

    @Override
    public List<mUsers.Users> getPaginatedUsers(int limit, int offset){
        try {
            List<mUsers.Users> users =_jdbctemplate.query(PAGINATED_SQL, new Object[]{limit, offset}, new UsersRowMapper());
            return users;
        }catch (Exception ex){
            throw ex;
        }
    }
    @Override
    public int getTotalUsers(){
        try {
            int users = _jdbctemplate.queryForObject(COUNT_SQL, Integer.class);
            return users;
        }catch (Exception ex){
            throw ex;
        }
    }
}
