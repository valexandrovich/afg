package ua.com.valexa.db.model.data.attribute.address;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.attribute.Attribute;

@Entity
@Table(schema = "data", name = "address",indexes = {
        @Index(name = "address__country__index", columnList = "country"),
        @Index(name = "address__region__index", columnList = "region"),
        @Index(name = "address__county__index", columnList = "county"),
        @Index(name = "address__city_type__index", columnList = "city_type"),
        @Index(name = "address__city__index", columnList = "city"),
        @Index(name = "address__street_type__index", columnList = "street_type"),
        @Index(name = "address__street__index", columnList = "street"),
        @Index(name = "address__building_number__index", columnList = "building_number"),
        @Index(name = "address__building_letter__index", columnList = "building_letter"),
        @Index(name = "address__building_part__index", columnList = "building_part"),
        @Index(name = "address__apartment__index", columnList = "apartment")
},
        uniqueConstraints = @UniqueConstraint(name = "address__full__uindex", columnNames = {
                "country",
                "region",
                "county",
                "city_type",
                "city",
                "street_type",
                "street",
                "building_number",
                "building_letter",
                "building_part",
                "apartment"
        })
)
@Data
@DiscriminatorValue("ADDRESS")
public class Address extends Attribute {
    @Column(name = "country", length = 255)
    private String country;
    @Column(name = "region", length = 255)
    private String region;
    @Column(name = "county", length = 255)
    private String county;
    @Column(name = "city_type", length = 255)
    private String cityType;
    @Column(name = "city", length = 255)
    private String city;
    @Column(name = "street_type", length = 255)
    private String streetType;
    @Column(name = "street", length = 255)
    private String street;
    @Column(name = "building_number", length = 255)
    private String buildingNumber;
    @Column(name = "building_letter", length = 255)
    private String buildingLetter;
    @Column(name = "building_part", length = 255)
    private String buildingPart;
    @Column(name = "apartment", length = 255)
    private String apartment;
}
