package ua.com.valexa.db.service.data.attribute.person_name;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;
import ua.com.valexa.db.repository.data.attribute.person_name.PersonNameLinkRepository;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class PersonNameLinkService {


    @Autowired
    PersonNameLinkRepository personNameLinkRepository;
    @Async
    @Transactional
    public CompletableFuture<Void> saveAll(Set<PersonNameLink> personNameLinks){
        return CompletableFuture.runAsync(()->{
            personNameLinkRepository.saveAll(personNameLinks);
        });
    }

    @Async
    @Transactional
    public CompletableFuture<Void> save(PersonNameLink personNameLink){
        return CompletableFuture.runAsync(()->{
                personNameLinkRepository.save(personNameLink);
        });
    }

}
