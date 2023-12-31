package ua.com.valexa.db.service.data.attribute.address_simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.address_simple.AddressSimplePersonLink;
import ua.com.valexa.db.model.data.attribute.birthplace.BithplaceLink;
import ua.com.valexa.db.repository.data.attribute.address_simple.AddressSimplePersonLinkRepository;
import ua.com.valexa.db.repository.data.attribute.birthplace.BithplaceLinkRepository;

import java.util.Optional;

@Service
public class AddressSimplePersonLinkService {
    @Autowired
    AddressSimplePersonLinkRepository addressSimplePersonLinkRepository;

    public AddressSimplePersonLink save(AddressSimplePersonLink addressSimplePersonLink) {
        try {
            return addressSimplePersonLinkRepository.save(addressSimplePersonLink);
        } catch (DataIntegrityViolationException e) {
            Optional<AddressSimplePersonLink> stored = addressSimplePersonLinkRepository.findByPrivatePersonIdAndAddressSimpleIdAndSource(
                    addressSimplePersonLink.getPrivatePerson().getId(),
                    addressSimplePersonLink.getAddressSimple().getId(),
                    addressSimplePersonLink.getSource()
            );
            return stored.orElse(null);
        }
    }
}
