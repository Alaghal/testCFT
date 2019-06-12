package com.test.cft.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CITY")
public class City {
    @Id
    @Column(name = "CITY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "CITY_NAME")
    private String cityName;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addresses ;

    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("City [id=" + id + ", cityName = " + cityName+" ]");
        stringBuilder.append( ", Addresses = {" );
        if ( addresses==null || addresses.isEmpty()) {

            stringBuilder.append( " }]" );

        } else {

            for (var address : addresses) {
                stringBuilder.append( " " + address + "," );
            }
            int lastComma = stringBuilder.length() - 1;
            stringBuilder.deleteCharAt( lastComma );
            stringBuilder.append( " }]" );
        }
        return stringBuilder.toString();
    }



}
