package ua.com.valexa.db.service.data.attribute.birthday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.birthday.BirthdayPersonLink;
import ua.com.valexa.db.repository.data.attribute.birthday.PersonBirthdayLinkRepository;

import java.util.Optional;

@Service
public class PersonBirthdayLinkService {
    @Autowired
    PersonBirthdayLinkRepository personBirthdayLinkRepository;

    public BirthdayPersonLink save(BirthdayPersonLink birthdayPersonLink) {
        try {
            return personBirthdayLinkRepository.save(birthdayPersonLink);
        } catch (DataIntegrityViolationException e) {
            Optional<BirthdayPersonLink> storedPbl = personBirthdayLinkRepository.findByPrivatePersonIdAndBirthdayIdAndSource(
                    birthdayPersonLink.getPrivatePerson().getId(),
                    birthdayPersonLink.getBirthday().getId(),
                    birthdayPersonLink.getSource()
            );
            return storedPbl.orElse(null);
        }
    }

    public BirthdayPersonLink save2(BirthdayPersonLink birthdayPersonLink) {
       return personBirthdayLinkRepository.save(birthdayPersonLink);
    }
}
