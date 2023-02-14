package org.example.repository;

import org.example.dto.Terminal;
import org.example.dto.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class TransactionRepository {
    private final SessionFactory sessionFactory;

    public TransactionRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int reFill(Transaction transaction) {


        Session session = sessionFactory.openSession();
        org.hibernate.Transaction transaction1 = session.beginTransaction();

        session.save(transaction);


        transaction1.commit();
        session.close();
//        String sql = "insert into transaction(card_number,amount,type) values (?,?,?)";
//        return jdbcTemplate.update(sql, transaction.getCard_number(), transaction.getAmount(), transaction.getTransactionType());

        return 1;
    }

    public List<Transaction> get_transaction_list_from_db() {


        Session session = sessionFactory.openSession();
        Query from_terminal_ = session.createQuery("from Transaction ");
        List<Transaction> resultList = from_terminal_.getResultList();

        session.close();
        return resultList;

//        String sql = "select * from transaction ";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transaction.class));


    }


    public int addTransaction(Transaction transaction) {

        Session session = sessionFactory.openSession();
        org.hibernate.Transaction transaction1 = session.beginTransaction();

        session.save(transaction);


        transaction1.commit();
        session.close();
        return 1;

//                String sql = "insert into transaction(card_number,amount,type,created_date,terminal_code) values (?,?,?,?,?);";
//
//        return jdbcTemplate.update(sql, transaction.getCard_number(), transaction.getAmount(), transaction.getTransactionType().name(),
//                Timestamp.valueOf(transaction.getCreated_date()), transaction.getTerminal_code());


    }

    public List<Transaction> get_profile_transaction_list_fromDb(String phone) {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Transaction where card_number in (select number from Card  where phone=:phone)");
        query.setParameter("phone", phone);
        List<Transaction> resultList = query.getResultList();

        session.close();
        return resultList;


        //        String sql = String.format("select * from transaction  where card_number in (select number from card where phone='%s' ) order by created_date DESC;", phone);
//
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transaction.class));

    }

    public int make_payment(Transaction transaction, String phone) {
//
//        Session session = sessionFactory.openSession();
//        session.createQuery("call")

        return 0;
//        String sql = "call transfer_mony(?,?,?,?,?,?,?)";
//        return jdbcTemplate.update(sql, transaction.getCard_number(), "444",
//                transaction.getAmount(), transaction.getTransactionType().name(),
//                Timestamp.valueOf(transaction.getCreated_date()), transaction.getTerminal_code(), phone);

    }


}
