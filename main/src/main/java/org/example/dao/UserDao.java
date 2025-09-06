package org.example.dao;

import org.example.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class UserDao {

    private final JdbcTemplate jdbc;

    private final static RowMapper<User> userMapper = (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("username"));

    public UserDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> getAllUsers() {
        return jdbc.query("SELECT id, username FROM users", userMapper);
    }

    public User getUser(Long id) {
        return jdbc.queryForObject("SELECT id, username FROM users WHERE id=?", userMapper, id);
    }

    public Long insertUser(String username) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbc).withTableName("users").usingGeneratedKeyColumns("id");
        Number id = simpleJdbcInsert.executeAndReturnKey(new HashMap<>() {{
            put("username", username);
        }});
        return id.longValue();
    }

    public void updateUser(Long id, String username) {
        jdbc.update("UPDATE users SET username=? WHERE id=?", username, id);
    }

    public void deleteUser(Long id) {
        jdbc.update("DELETE FROM users WHERE id=?", id);
    }
}
