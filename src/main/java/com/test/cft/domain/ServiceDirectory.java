package com.test.cft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"serviceStations"})
@Table(name = "SERVICE_DIRECTORY")
public class ServiceDirectory {
    @Id
    @Column(name = "SERVICE_DIRECTORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "SERVICE_DIRECTORY_NAME")
    private String serviceDirectoryName;


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable (name="SERVICE_DIRECTORY_SERVICE_STATION",
            joinColumns=@JoinColumn (name="SERVICE_DIRECTORY_ID",referencedColumnName = "SERVICE_DIRECTORY_ID"),
            inverseJoinColumns=@JoinColumn(name="SERVICE_STATION_ID",referencedColumnName = "SERVICE_STATION_ID"))
    @JsonIgnoreProperties("serviceDirectories")
    private List<ServiceStation> serviceStations;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder( "SERVICE_DIRECTORY [id=" + id + ", serviceDirectoryName = " + serviceDirectoryName + " ]" );
        return stringBuilder.toString();

    }

    public ServiceDirectory(String name, ServiceStation... serviceStations  ){
        this.serviceDirectoryName = name;
        this.serviceStations = Stream.of(serviceStations).collect( Collectors.toList());
        this.serviceStations.forEach( x-> x.getServiceDirectories().add(this) );
    }

}
