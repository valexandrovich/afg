package ua.com.valexa.db.service.data.attribute.local_passport;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.local_passport.LocalPassport;
import ua.com.valexa.db.model.data.attribute.local_passport.LocalPassportLink;
import ua.com.valexa.db.repository.data.attribute.local_passport.LocalPassportLinkRepository;
import ua.com.valexa.db.repository.data.attribute.local_passport.LocalPassportRepository;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class LocalPassportLinkService {
    @Autowired
    LocalPassportLinkRepository localPassportLinkRepository;

    @Async
    @Transactional
    public CompletableFuture<Void> saveAll(Set<LocalPassportLink> localPassportLinks){
        return CompletableFuture.runAsync(()->{
            localPassportLinkRepository.saveAll(localPassportLinks);
        });
    }
}
