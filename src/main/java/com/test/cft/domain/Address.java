package com.test.cft.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ADDRESS")
public class Address {
    @Id
    @Column(name = "ADDRESS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ADDRESS_NAME")
    private String addressName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CITY_ID")
    private City city;

    @OneToOne(mappedBy = "ADDRESS", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     private  Address address;

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("Address [id=" + id + ", addressName = " + addressName+" ]");
        return stringBuilder.toString();
    }





}
