package com.test.cft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"addresses", "country"})
@Table(name = "CITY")
public class City {
    @Id
    @Column(name = "CITY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "CITY_NAME")
    private String cityName;


    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("city")
    private List<Address> addresses ;

    @ManyToOne ( fetch = FetchType.EAGER)
    @JoinColumn(name = "COUNTRY_ID")
    @JsonIgnoreProperties("cityList")
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
