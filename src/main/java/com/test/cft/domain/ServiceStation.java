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
@Table(name = "SERVICE_STATION")
public class ServiceStation {
    @Id
    @Column(name = "SERVICE_STATION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "SERVICE_STATION_NAME")
    private String serviceStationName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SERVICE_DIRECTORY_SERVICE_STATION",
            joinColumns = @JoinColumn(name = "SERVICE_STATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "SERVICE_DIRECTORY_ID"))
    private List<ServiceDirectory> serviceDirectories;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

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

}
