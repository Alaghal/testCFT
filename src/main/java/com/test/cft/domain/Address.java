package com.test.cft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"city", "serviceStation"})
@Table(name = "ADDRESS")
public class Address {
    @Id
    @Column(name = "ADDRESS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ADDRESS_NAME")
    private String addressName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CITY_ID", insertable = false, updatable = false)
    @JsonIgnoreProperties("addresses")
    private City city;


    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("address")
    private  ServiceStation serviceStation;

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("Address [id=" + id + ", addressName = " + addressName+" ]");
        return stringBuilder.toString();
    }





}
