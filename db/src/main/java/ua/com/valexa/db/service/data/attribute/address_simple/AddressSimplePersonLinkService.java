package ua.com.valexa.db.service.data.attribute.address_simple;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.address_simple.AddressSimplePersonLink;
import ua.com.valexa.db.model.data.attribute.birthplace.BithplaceLink;
import ua.com.valexa.db.repository.data.attribute.address_simple.AddressSimplePersonLinkRepository;
import ua.com.valexa.db.repository.data.attribute.birthplace.BithplaceLinkRepository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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

    @Async
    @Transactional
    public CompletableFuture<Void> saveAsync(AddressSimplePersonLink addressSimplePersonLink){
        return CompletableFuture.runAsync(()->{
//            long startTime = System.currentTimeMillis();
            addressSimplePersonLinkRepository.save(addressSimplePersonLink);
//            long endTime = System.currentTimeMillis(); // Get end time
//            long duration = endTime - startTime;
//            long minutes = duration / 60000;
//            long seconds = (duration % 60000) / 1000;
//            long milliseconds = duration % 1000;
//            System.out.printf("saveAsync(AddressSimplePersonLink addressSimplePersonLink): " + "%02d:%02d:%03d%n", minutes, seconds, milliseconds);
        });
    }
}
