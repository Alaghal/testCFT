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
@EqualsAndHashCode(exclude = {"cityList"})
@Table(name = "COUNTRY")
public class Country {
    @Id
    @Column(name = "COUNTRY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "COUNTRY_NAME")
    private String countryName;


    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("country")
    List<City> cityList;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Country [id=" + id + ", countryName = " + countryName+" ]");
        stringBuilder.append( ", Cities = {" );
        if ( cityList==null || cityList.isEmpty()) {

            stringBuilder.append( " }]" );

        } else {

            for (var city : cityList) {
                stringBuilder.append( " " + city + "," );
            }
            int lastComma = stringBuilder.length() - 1;
            stringBuilder.deleteCharAt( lastComma );
            stringBuilder.append( " }]" );
        }
        return stringBuilder.toString();

    }
}
