package com.test.cft.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"serviceDirectories","addresses"})
@Table(name = "SERVICE_STATION")
public class ServiceStation {
    @Id
    @Column(name = "SERVICE_STATION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "SERVICE_STATION_NAME")
    private String serviceStationName;

    @ManyToMany(mappedBy = "serviceStations",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("serviceStations")
    private List<ServiceDirectory> serviceDirectories;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_ID")
    @JsonIgnoreProperties("serviceStation")
    private Address addresses;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ServiceStation [id=" + id + ", ServiceStationName = " + serviceStationName+" ]");
        stringBuilder.append( ", serviceDirectories = {" );
        if ( serviceDirectories==null || serviceDirectories.isEmpty()) {

            stringBuilder.append( " }]" );

        } else {

            for (var serviceDirectory : serviceDirectories) {
                stringBuilder.append( " " + serviceDirectory + "," );
            }
            int lastComma = stringBuilder.length() - 1;
            stringBuilder.deleteCharAt( lastComma );
            stringBuilder.append( " }]" );
        }
        return stringBuilder.toString();

    }
     public ServiceStation(String name){
        this.serviceStationName=name;
     }
}
