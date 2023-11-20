package ua.com.valexa.db.service.data.base_object;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;
import ua.com.valexa.db.model.data.base_object.PrivatePerson;
import ua.com.valexa.db.repository.data.base_object.PrivatePersonRepository;
import ua.com.valexa.db.service.data.attribute.person_name.PersonNameService;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class PrivatePersonService {

    @Autowired
    PrivatePersonRepository privatePersonRepository;

    @Autowired
    PersonNameService personNameService;


    @Async
    @Transactional
    public CompletableFuture<Void> saveAll(Set<PrivatePerson> privatePersons) {
        return CompletableFuture.runAsync(() -> {
            privatePersonRepository.saveAll(privatePersons);
        });
    }

    @Async
    @Transactional
    public CompletableFuture<PrivatePerson> save(PrivatePerson privatePerson) {
        return CompletableFuture.supplyAsync(() -> {
            return privatePersonRepository.save(privatePerson);
        });
    }
    @Autowired
    private TransactionTemplate transactionTemplate;

//    @Async
//    @Transactional
//    public CompletableFuture<Set<PrivatePerson>> findCandidatesByNoVowelsHashes(Set<String> noVowelsHashes) {
//        return CompletableFuture.supplyAsync(() -> {
//            CompletableFuture<Set<PersonName>> personNamesFuture = personNameService.findByNoVowelsHashes(noVowelsHashes);
//            Set<PersonName> personNames = personNamesFuture.join();
//            return personNames.stream()
//                    .flatMap(personName -> personName.getPersonNameLinks().stream()
//                            .map(PersonNameLink::getPrivatePerson))
//                    .collect(Collectors.toSet());
//        });
//    }


    @Async
//    @Transactional
    public CompletableFuture<Set<PrivatePerson>> findCandidatesByNoVowelsHashes(Set<String> noVowelsHashes) {
        return CompletableFuture.supplyAsync(() ->
        transactionTemplate.execute(status -> {
            CompletableFuture<Set<PersonName>> personNamesFuture = personNameService.findByNoVowelsHashes(noVowelsHashes);
            Set<PersonName> personNames = personNamesFuture.join();
            return personNames.stream()
                    .flatMap(personName -> personName.getPersonNameLinks().stream()
                            .map(PersonNameLink::getPrivatePerson))
                    .collect(Collectors.toSet());
        }));
    }

}
