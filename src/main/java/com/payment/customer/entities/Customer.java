package com.payment.customer.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public Long id;
    public String name;
    public String phoneNumber;
}
