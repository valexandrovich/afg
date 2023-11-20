package ua.com.valexa.db.service.data.attribute.address;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.address.Address;
import ua.com.valexa.db.model.data.attribute.address_simple.AddressSimple;
import ua.com.valexa.db.repository.data.attribute.address.AddressRepository;
import ua.com.valexa.db.repository.data.attribute.address_simple.AddressSimpleRepository;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Async
    @Transactional
    public CompletableFuture<Void> saveAll(Set<Address> addresses){
        return CompletableFuture.runAsync(()->{
            addressRepository.saveAll(addresses);
        });
    }
}
