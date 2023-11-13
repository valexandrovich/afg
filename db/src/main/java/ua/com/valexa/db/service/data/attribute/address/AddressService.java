package ua.com.valexa.db.service.data.attribute.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.address.Address;
import ua.com.valexa.db.repository.data.attribute.address.AddressRepository;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public Address save(Address address) {
        try {
            return addressRepository.save(address);
        } catch (DataIntegrityViolationException e) {
            Optional<Address> stored = addressRepository.findByCountryAndRegionAndCountyAndCityTypeAndCityAndStreetTypeAndStreetAndBuildingNumberAndBuildingLetterAndBuildingPartAndApartment(
                    address.getCountry(),
                    address.getRegion(),
                    address.getCounty(),
                    address.getCityType(),
                    address.getCity(),
                    address.getStreetType(),
                    address.getStreet(),
                    address.getBuildingNumber(),
                    address.getBuildingLetter(),
                    address.getBuildingPart(),
                    address.getApartment()
            );
            return stored.orElse(null);
        }
    }
}
