package ua.com.valexa.db.service.data.attribute.address_simple;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.address_simple.AddressSimple;
import ua.com.valexa.db.model.data.attribute.birthday.Birthday;
import ua.com.valexa.db.repository.data.attribute.address_simple.AddressSimpleRepository;
import ua.com.valexa.db.repository.data.attribute.birthday.BirthdayRepository;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class AddressSimpleService {
    @Autowired
    AddressSimpleRepository addressSimpleRepository;

    @Async
    @Transactional
    public CompletableFuture<Void> saveAll(Set<AddressSimple> addressSimples){
        return CompletableFuture.runAsync(()->{
            addressSimpleRepository.saveAll(addressSimples);
        });
    }
}
