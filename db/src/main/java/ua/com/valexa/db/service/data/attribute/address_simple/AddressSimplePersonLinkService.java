package ua.com.valexa.db.service.data.attribute.address_simple;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.address_simple.AdressSimplePersonLink;
import ua.com.valexa.db.model.data.attribute.birthday.BirthdayLink;
import ua.com.valexa.db.repository.data.attribute.address_simple.AddressSimplePersonLinkRepository;
import ua.com.valexa.db.repository.data.attribute.birthday.BirthdayLinkRepository;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class AddressSimplePersonLinkService {
    @Autowired
    AddressSimplePersonLinkRepository addressSimplePersonLinkRepository;

    @Async
    @Transactional
    public CompletableFuture<Void> saveAll(Set<AdressSimplePersonLink> adressSimplePersonLinks){
        return CompletableFuture.runAsync(()->{
            addressSimplePersonLinkRepository.saveAll(adressSimplePersonLinks);
        });
    }
}