package ua.com.valexa.db.service.data.attribute.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.address.AddressPersonLink;
import ua.com.valexa.db.model.data.attribute.address_simple.AddressSimplePersonLink;
import ua.com.valexa.db.repository.data.attribute.address.AddressPersonLinkRepository;
import ua.com.valexa.db.repository.data.attribute.address_simple.AddressSimplePersonLinkRepository;

import java.util.Optional;

@Service
public class AddressPersonLinkService {
    @Autowired
    AddressPersonLinkRepository addressPersonLinkRepository;

    public AddressPersonLink save(AddressPersonLink addressPersonLink) {
        try {
            return addressPersonLinkRepository.save(addressPersonLink);
        } catch (DataIntegrityViolationException e) {
            Optional<AddressPersonLink> stored = addressPersonLinkRepository.findByPrivatePersonIdAndAddressIdAndSource(
                    addressPersonLink.getPrivatePerson().getId(),
                    addressPersonLink.getAddress().getId(),
                    addressPersonLink.getSource()
            );
            return stored.orElse(null);
        }
    }
}
