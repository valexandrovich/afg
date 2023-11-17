package ua.com.valexa.db.service.data.attribute.address;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.address.AddressPersonLink;
import ua.com.valexa.db.model.data.attribute.address_simple.AddressSimplePersonLink;
import ua.com.valexa.db.repository.data.attribute.address.AddressPersonLinkRepository;
import ua.com.valexa.db.repository.data.attribute.address_simple.AddressSimplePersonLinkRepository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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

    @Async
    @Transactional
    public CompletableFuture<Void> saveAsync(AddressPersonLink addressPersonLink){
        return CompletableFuture.runAsync(()->{
//            long startTime = System.currentTimeMillis();
            addressPersonLinkRepository.save(addressPersonLink);
//            long endTime = System.currentTimeMillis(); // Get end time
//            long duration = endTime - startTime;
//            long minutes = duration / 60000;
//            long seconds = (duration % 60000) / 1000;
//            long milliseconds = duration % 1000;
//            System.out.printf("saveAsync(AddressPersonLink addressPersonLink): " + "%02d:%02d:%03d%n", minutes, seconds, milliseconds);
        });
    }
}
