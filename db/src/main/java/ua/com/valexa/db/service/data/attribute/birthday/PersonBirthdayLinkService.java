package ua.com.valexa.db.service.data.attribute.birthday;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.birthday.BirthdayPersonLink;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;
import ua.com.valexa.db.repository.data.attribute.birthday.BirthdayPersonLinkRepository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class PersonBirthdayLinkService {
    @Autowired
    BirthdayPersonLinkRepository birthdayPersonLinkRepository;

    public BirthdayPersonLink save(BirthdayPersonLink birthdayPersonLink) {
        try {
            return birthdayPersonLinkRepository.save(birthdayPersonLink);
        } catch (DataIntegrityViolationException e) {
            Optional<BirthdayPersonLink> storedPbl = birthdayPersonLinkRepository.findByPrivatePersonIdAndBirthdayIdAndSource(
                    birthdayPersonLink.getPrivatePerson().getId(),
                    birthdayPersonLink.getBirthday().getId(),
                    birthdayPersonLink.getSource()
            );
            return storedPbl.orElse(null);
        }
    }

    public BirthdayPersonLink save2(BirthdayPersonLink birthdayPersonLink) {
       return birthdayPersonLinkRepository.save(birthdayPersonLink);
    }


    @Async
    @Transactional
    public CompletableFuture<Void> saveAsync(BirthdayPersonLink birthdayPersonLink){
        return CompletableFuture.runAsync(()->{
            birthdayPersonLinkRepository.save(birthdayPersonLink);
        });
    }


}
