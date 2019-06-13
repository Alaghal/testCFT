package com.test.cft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SERVICE_DIRECTORY")
public class ServiceDirectory {
    @Id
    @Column(name = "SERVICE_DIRECTORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "SERVICE_DIRECTORY_NAME")
    private String serviceDirectoryName;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name="SERVICE_DIRECTORY_SERVICE_STATION",
            joinColumns=@JoinColumn (name="SERVICE_DIRECTORY_ID"),
            inverseJoinColumns=@JoinColumn(name="SERVICE_STATION_ID"))
    private List<ServiceStation>  serviceStations;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder( "SERVICE_DIRECTORY [id=" + id + ", countryName = " + serviceDirectoryName + " ]" );
        return stringBuilder.toString();

    }
}
