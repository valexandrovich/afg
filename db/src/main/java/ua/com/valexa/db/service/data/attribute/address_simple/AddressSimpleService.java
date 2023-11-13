package ua.com.valexa.db.service.data.attribute.address_simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.address_simple.AddressSimple;
import ua.com.valexa.db.model.data.attribute.birthday.Birthday;
import ua.com.valexa.db.repository.data.attribute.address_simple.AddressSimpleRepository;
import ua.com.valexa.db.repository.data.attribute.birthday.BirthdayRepository;

import java.util.Optional;

@Service
public class AddressSimpleService {
    @Autowired
    AddressSimpleRepository addressSimpleRepository;

    public AddressSimple save(AddressSimple addressSimple) {
        try {
            return addressSimpleRepository.save(addressSimple);
        } catch (DataIntegrityViolationException e) {
            Optional<AddressSimple> stored = addressSimpleRepository.findByAddress(
                    addressSimple.getAddress()
            );
            return stored.orElse(null);
        }
    }
}
