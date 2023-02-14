package org.example.repository;


import org.example.dto.Profile;

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
public class ProfileRepository {


    private final SessionFactory sessionFactory;

    public ProfileRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Profile searchByPhone(String phone) {


        Session session = sessionFactory.openSession();
        Query from_profile_ = session.createQuery("From Profile where phone=:phone ");
        from_profile_.setParameter("phone", phone);

        List<Profile> resultList = from_profile_.getResultList();

        if (!resultList.isEmpty()) {
            return resultList.get(0);
        }
        return null;


//        String sql = "select count(*) from profile where phone='" + phone + "' ;";
//
//        int integer = jdbcTemplate.queryForObject(sql, Integer.class);
//        if (integer != 0) {
//            sql="select * from profile where phone='" + phone + "' ;";
//            Profile profile= jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Profile.class));
//            return profile;
//        }
//        return null;

    }

    public int addProfileToDb(Profile profile) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(profile);

        transaction.commit();
        session.close();
        return 1;

//        String sql = "insert into profile(name,surname,phone,password,created_date,status,role) " +
//                "values (?,?,?,?,?,?,?)";
//        return jdbcTemplate.update(sql, profile.getName(), profile.getSurname(), profile.getPhone(),
//                profile.getPassword(), Timestamp.valueOf(profile.getCreated_date()), profile.getStatus().name(), profile.getRole().name());

    }


    public List<Profile> get_profile_list_fromDb() {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from  Profile ");

        return (List<Profile>) query.getResultList();

//        String sql = "select * from profile ";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Profile.class));

    }
}
