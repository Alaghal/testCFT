package com.test.cft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "COUNTRY")
public class Country {
    @Id
    @Column(name = "COUNTRY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @JsonIgnore
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
