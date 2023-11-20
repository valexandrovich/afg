package ua.com.valexa.db.service.data.attribute.birthplace;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.birthplace.BirthplaceLink;
import ua.com.valexa.db.repository.data.attribute.birthplace.BirthplaceLinkRepository;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class BirthplaceLinkService {

    @Autowired
    BirthplaceLinkRepository birthplaceLinkRepository;

    @Async
    @Transactional
    public CompletableFuture<Void> saveAll(Set<BirthplaceLink> birthplaceLinks){
        return CompletableFuture.runAsync(()->{
            birthplaceLinkRepository.saveAll(birthplaceLinks);
        });
    }
}
