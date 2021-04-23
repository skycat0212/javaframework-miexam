package kr.ac.jejunu.userdao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;

public class UserDao {
    private final JdbcTemplate jdbcContext;

    public UserDao(JdbcTemplate jdbcContext){
        this.jdbcContext = jdbcContext;
    }

    public User get(Integer id) throws SQLException {
        String sql = "select * from userinfo2 where id = ?";
        Object[] params = new Object[]{ id };
        return jdbcContext.query(sql, params, rs -> {
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            } return user;
        });
    }

    public void insert(User user) throws SQLException {
        String sql = "insert into userinfo2 (name, password) values (?,?)";
        Object[] params = new Object[]{ user.getName(), user.getPassword() };
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcContext.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length ; i ++) {
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
    }

    public void update(User user) throws SQLException {
        String sql = "update userinfo2 set name = ?, password = ? where id =?";
        Object[] params = new Object[]{ user.getName(), user.getPassword(), user.getId() };
        jdbcContext.update(sql, params);
    }

    public void delete(Integer id) throws SQLException {
        String sql = "delete from userinfo2 where id = ?";
        Object[] params = new Object[]{ id };
        jdbcContext.update(sql, params);
    }

}
