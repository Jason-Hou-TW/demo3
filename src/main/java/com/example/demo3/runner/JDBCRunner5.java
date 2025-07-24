package com.example.demo3.runner;

import com.example.demo3.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(5)
public class JDBCRunner5 implements CommandLineRunner {
    @Autowired
    private NamedParameterJdbcTemplate template;
    private static final String SQL = "SELECT id, username, password from USERS2 WHERE id=:id";

    @Override
    public void run(String... args) throws Exception {
        SqlParameterSource source = new MapSqlParameterSource().addValue("id", 1);
        User u1 = template.queryForObject(SQL, source, (rs, rowNum) -> new User(rs.getString("username"),
                rs.getString("password")));
        log.info("user name={}, password={}", u1.getUsername(), u1.getPassword());
    }
}