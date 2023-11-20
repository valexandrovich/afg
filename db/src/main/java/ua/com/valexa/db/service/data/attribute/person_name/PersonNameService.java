package ua.com.valexa.db.service.data.attribute.person_name;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;
import ua.com.valexa.db.repository.data.attribute.person_name.PersonNameRepository;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class PersonNameService {

    @Autowired
    PersonNameRepository personNameRepository;

    @Async
    @Transactional
    public CompletableFuture<Void> saveAll(Set<PersonName> personNames){
        return CompletableFuture.runAsync(()->{
            personNameRepository.saveAll(personNames);
        });
    }
    @Async
    @Transactional
    public CompletableFuture<Void> save(PersonName personName){
        return CompletableFuture.runAsync(()->{
            personNameRepository.save(personName);
        });
    }

    @Async
    @Transactional
    public CompletableFuture<Set<PersonName>> findByNoVowelsHashes(Set<String> noVowelsHashes){
        return CompletableFuture.supplyAsync(()->{
            return personNameRepository.findByNoVowelsHashIn(noVowelsHashes);
        });
    }

}
