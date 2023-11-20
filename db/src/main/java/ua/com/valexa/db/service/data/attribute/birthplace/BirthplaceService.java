package ua.com.valexa.db.service.data.attribute.birthplace;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.birthplace.Birthplace;
import ua.com.valexa.db.repository.data.attribute.birthplace.BirthplaceRepository;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class BirthplaceService {

    @Autowired
    BirthplaceRepository   birthplaceRepository;

    @Async
    @Transactional
    public CompletableFuture<Void> saveAll(Set<Birthplace> birthplaces){
        return CompletableFuture.runAsync(()->{
            birthplaceRepository.saveAll(birthplaces);
        });
    }

}
