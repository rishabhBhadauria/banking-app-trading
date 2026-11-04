package com.banking.trader.persist.entity.persist;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "trader")
@Getter
@Setter
public class Trader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @Column(unique = true)
    private String email;
    
    private Double balance;
    
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
    // Getters and Setters
}