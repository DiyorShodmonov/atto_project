package org.example.repository;

import org.example.dto.Terminal;
import org.example.enums.GeneralStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.Timestamp;
import java.util.List;

@Repository
public class TerminalRepository {

    private final SessionFactory sessionFactory;

    public TerminalRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int addTerminalToDb(Terminal terminal) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(terminal);

        transaction.commit();
        session.close();
        return 1;
        //        String sql = "insert into terminal(code,address,created_date,status) " +
//                "values (?,?,?,?)";
//        return jdbcTemplate.update(sql, terminal.getCode(), terminal.getAddress(), Timestamp.valueOf(terminal.getCreated_date()), terminal.getStatus().name());
    }


    public Terminal getTerminal(String code) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Terminal where code=:code");
        query.setParameter("code", code);

        List<Terminal> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);

        }

        return null;

        //        String sql = "select count(*) from terminal where code='" + code + "' ;";
//
//        int integer = jdbcTemplate.queryForObject(sql, Integer.class);
//
//        if (integer == 0) {
//            return null;
//        }
//        sql = "select * from terminal where code='" + code + "' ;";
//        ;
//        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Terminal.class));
    }

    public List<Terminal> get_terminal_list_fromDb() {

        Session session = sessionFactory.openSession();
        Query from_terminal_ = session.createQuery("from Terminal");
        List<Terminal> resultList = from_terminal_.getResultList();

        session.close();
        return resultList;

//        String sql = "select * from terminal ";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Terminal.class));
    }

    public int updateTerminal_address_fromDB(String code, String address) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("update Terminal set address=:a where code=:c");
        query.setParameter("a", address);
        query.setParameter("c", code);

        int i = query.executeUpdate();
        transaction.commit();
        session.close();
        return i;


        //        String sql = "update terminal set address=? where code=? ;";
//        return jdbcTemplate.update(sql, address, code);
    }

    public int changeTerminal_status_fromDB(String code, GeneralStatus status) {

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("update Terminal set status=:s where code=:c");
        query.setParameter("s", status);
        query.setParameter("c", code);

        int i = query.executeUpdate();
        transaction.commit();
        session.close();
        return i;
//
//        String sql = "update terminal set status=? where code=? ;";
//        return jdbcTemplate.update(sql, status, code);
    }

    public int deleteTerminal_fromDb(String code) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("delete Terminal where code=:c");
        query.setParameter("c", code);
        int i = query.executeUpdate();
        transaction.commit();
        session.close();
        return i;


        //        String sql = "delete from terminal where code=? ;";
//        return jdbcTemplate.update(sql, code);
    }
}
