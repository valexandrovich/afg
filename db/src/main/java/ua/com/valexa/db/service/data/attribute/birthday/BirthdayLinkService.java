package ua.com.valexa.db.service.data.attribute.birthday;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.birthday.BirthdayLink;
import ua.com.valexa.db.repository.data.attribute.birthday.BirthdayLinkRepository;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class BirthdayLinkService {
    @Autowired
    BirthdayLinkRepository birthdayLinkRepository;

    @Async
    @Transactional
    public CompletableFuture<Void> saveAll(Set<BirthdayLink> birthdayLinks){
        return CompletableFuture.runAsync(()->{
            birthdayLinkRepository.saveAll(birthdayLinks);
        });
    }
}
