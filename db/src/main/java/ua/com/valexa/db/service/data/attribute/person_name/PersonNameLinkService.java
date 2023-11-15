package ua.com.valexa.db.service.data.attribute.person_name;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;
import ua.com.valexa.db.repository.data.attribute.person_name.PersonNameLinkRepository;

import java.util.concurrent.CompletableFuture;

@Service
public class PersonNameLinkService {
    @Autowired
    PersonNameLinkRepository personNameLinkRepository;

//    public PersonNameLink save(PersonNameLink personNameLink) {
//        try {
//            return personNameLinkRepository.save(personNameLink);
//        } catch (DataIntegrityViolationException e) {
//            Optional<PersonNameLink> storedPnl = personNameLinkRepository.findByPrivatePersonIdAndPersonNameIdAndSource(
//                personNameLink.getPrivatePerson().getId(),
//                personNameLink.getPersonName().getId(),
//                personNameLink.getSource()
//            );
//            return storedPnl.orElse(null);
//        }
//        // Other exceptions can be caught and handled as well
//    }

//    public PersonNameLink save2(PersonNameLink personNameLink) {
//       return personNameLinkRepository.save(personNameLink);
//    }

    @Async
    @Transactional
    public CompletableFuture<Void> save(PersonNameLink personNameLink){
        return CompletableFuture.runAsync(()->{
            personNameLinkRepository.save(personNameLink);
        });
    }
}
