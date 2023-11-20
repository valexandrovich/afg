package ua.com.valexa.db.service.data.attribute.address;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.address.AdressPersonLink;
import ua.com.valexa.db.repository.data.attribute.address.AddressPersonLinkRepository;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class AddressPersonLinkService {
    @Autowired
    AddressPersonLinkRepository addressPersonLinkRepository;

    @Async
    @Transactional
    public CompletableFuture<Void> saveAll(Set<AdressPersonLink> adressPersonLinks){
        return CompletableFuture.runAsync(()->{
            addressPersonLinkRepository.saveAll(adressPersonLinks);
        });
    }
}
