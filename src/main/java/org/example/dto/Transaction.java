package org.example.dto;

import org.example.enums.TransactionType;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(columnDefinition = "varchar references card(number)")
    private String card_number;

    @Column
    private Long amount;
    @Column
    private String terminal_code;

    @Column(columnDefinition = "varchar")
    private TransactionType transactionType;

    @Column
    private LocalDateTime created_date;

    public Transaction() {
    }

    public Transaction(String card_number, Long amount, String terminal_code, TransactionType transactionType, LocalDateTime created_date) {
        this.card_number = card_number;
        this.amount = amount;
        this.terminal_code = terminal_code;
        this.transactionType = transactionType;
        this.created_date = created_date;
    }

    public Transaction(Integer id, String card_number, Long amount, String terminal_code, TransactionType transactionType, LocalDateTime created_date) {
        this.id = id;
        this.card_number = card_number;
        this.amount = amount;
        this.terminal_code = terminal_code;
        this.transactionType = transactionType;
        this.created_date = created_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTerminal_code() {
        return terminal_code;
    }

    public void setTerminal_code(String terminal_code) {
        this.terminal_code = terminal_code;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", card_number='" + card_number + '\'' +
                ", amount=" + amount +
                ", terminal_code='" + terminal_code + '\'' +
                ", transactionType=" + transactionType +
                ", created_date=" + created_date +
                '}';
    }
}
