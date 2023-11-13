package ua.com.valexa.db.repository.data.attribute.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.address.Address;
import ua.com.valexa.db.model.data.attribute.address_simple.AddressSimple;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    Optional<Address>
    findByCountryAndRegionAndCountyAndCityTypeAndCityAndStreetTypeAndStreetAndBuildingNumberAndBuildingLetterAndBuildingPartAndApartment
            (
                    String country,
                    String region,
                    String county,
                    String cityType,
                    String city,
                    String streetType,
                    String street,
                    String buildingNumber,
                    String buildingLetter,
                    String buildingPart,
                    String apartment
            );

}
