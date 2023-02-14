package org.example.repository;

import org.example.dto.Card;
import org.example.enums.GeneralStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository
public class CardRepository {
    private final SessionFactory sessionFactory;

    public CardRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void addCardToDb(Card card) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(card);

        transaction.commit();
        session.close();

//        String sql = "insert into card(number,exp_date,created_date,status,balance) " + "values (?,?,?,?,?)";
//
//        return jdbcTemplate.update(sql, card.getNumber(), card.getExp_date(), Timestamp.valueOf(card.getCreated_date()), card.getStatus().name(), card.getBalance());

    }


    public List<Card> get_card_list() {

        Session session = sessionFactory.openSession();
        Query from_card_ = session.createQuery("FROM Card ");


        List<Card> resultList = from_card_.getResultList();

        session.close();
        return resultList;


        //        String sql = "select * from card ;";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Card.class));
    }


    public Card searchCardByNumber(String number) {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from  Card where  number=:number");
        query.setParameter("number", number);
        List<Card> resultList = query.getResultList();

        if (!resultList.isEmpty()) {
            return resultList.get(0);
        }
        session.close();
        return null;


//        String sql = "select count(*) from card where number= '" + number + "'";
//
//        int integer = jdbcTemplate.queryForObject(sql, Integer.class);
//        if (integer == 0) {
//            return null;
//        }
//
//        sql = "select * from card where number= '" + number + "'";
//        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Card.class));

    }

    public int updateCardFromDb(Card card) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();


        Query query = session.createQuery("update Card set exp_date=:e where number=:n");
        query.setParameter("e", card.getExp_date());
        query.setParameter("n", card.getNumber());
        int i = query.executeUpdate();
        transaction.commit();
        session.close();
        return i;


//        String sql = "update card set exp_date=? where number=? ;";
//        return jdbcTemplate.update(sql, card.getExp_date(), card.getNumber());

    }

    public int changeStatus(String number, GeneralStatus status) {


        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();


        Query query = session.createQuery("update Card set status=:s where number=:n");
        query.setParameter("s", status);
        query.setParameter("n", number);

        int i = query.executeUpdate();
        transaction.commit();
        session.close();
        return i;


//        String sql = "update card set status=? where number=? ;";
//        return jdbcTemplate.update(sql, status, number);

    }


    public int deleteCardFromDb(String number) {

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("delete Card where number=:number");
        query.setParameter("number", number);
        int i = query.executeUpdate();


        transaction.commit();
        session.close();
        return i;


//        String sql = "delete  from card  where number=? ;";
//        return jdbcTemplate.update(sql, number);

    }

    public int addPhone_to_Card(Card card) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("update Card set phone=:p, added_date=:a where number=:n");
        query.setParameter("p", card.getPhone());
        query.setParameter("a", card.getAdded_date());
        query.setParameter("n", card.getNumber());
        int i = query.executeUpdate();
        transaction.commit();
        session.close();
        return i;


//        String sql = "update card set phone=?, added_date=? where number=? ;";
//        return jdbcTemplate.update(sql, card.getPhone(), Timestamp.valueOf(card.getAdded_date()), card.getNumber());


    }

    public List<Card> get_profile_card_list_fromDb(String phone) {

        Session session = sessionFactory.openSession();


        Query from_card_ = session.createQuery("from Card ");
        List<Card> resultList = from_card_.getResultList();

        session.close();

        return resultList;
//        String sql = "select * from card where phone='" + phone + "'";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Card.class));

    }


    public int delete_phone_from_card(String number) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Card card = session.get(Card.class, number);
        if (card == null) {
            return 0;
        }

        session.delete(card);
        return 1;

//        String sql = "update   card set phone=null  where number=? ;";
//        return jdbcTemplate.update(sql, number);
    }

    public int refillCard(Long amount, String number) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();


        Query query = session.createQuery("update Card set balance=balance+:a where number=:n");
        query.setParameter("a", amount);
        query.setParameter("n", number);

        int i = query.executeUpdate();

        transaction.commit();
        session.close();

        return i;


//        String sql = " update card set balance=balance+? where number=? ;";
//        return jdbcTemplate.update(sql, amount, number);
    }
}
