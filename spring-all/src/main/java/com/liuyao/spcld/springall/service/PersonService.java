package com.liuyao.spcld.springall.service;

import com.liuyao.spcld.springall.eneity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Person getPerson(Integer id) {
        String sql = "select * from tb_person where id = ?";
        final List<Person> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Person>(Person.class), 1);
        return query.size() > 0 ? query.get(0) : null;
    }

    /**
     *
     *
     *
     * @param id
     * @return
     */
    @Transactional
    public Person save(Integer id) {
        String sql = "select * from tb_person where id = ?";
        final List<Person> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Person>(Person.class), 1);
        return query.size() > 0 ? query.get(0) : null;
    }

}
