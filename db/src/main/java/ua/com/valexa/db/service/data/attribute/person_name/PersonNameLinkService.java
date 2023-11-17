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

//            long startTime = System.currentTimeMillis();

            personNameLinkRepository.save(personNameLink);

//            long endTime = System.currentTimeMillis(); // Get end time
//            long duration = endTime - startTime;
//            long minutes = duration / 60000;
//            long seconds = (duration % 60000) / 1000;
//            long milliseconds = duration % 1000;
//            System.out.printf("save(PersonNameLink personNameLink): " + "%02d:%02d:%03d%n", minutes, seconds, milliseconds);
        });
    }
}
