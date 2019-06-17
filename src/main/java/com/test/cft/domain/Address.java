package com.test.cft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CITY_ID")
    @JsonIgnoreProperties("addresses")
    private City city;

    @OneToMany(mappedBy = "addresses", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("addresses")
    private List<ServiceStation> serviceStation;

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("Address [id=" + id + ", addressName = " + addressName+" ]");
        return stringBuilder.toString();
    }





}
