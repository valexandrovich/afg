package ua.com.valexa.db.service.data.attribute.person_name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;
import ua.com.valexa.db.repository.data.attribute.person_name.PersonNameLinkRepository;

import java.util.Optional;

@Service
public class PersonNameLinkService {
    @Autowired
    PersonNameLinkRepository personNameLinkRepository;

    public PersonNameLink savePersonNameLink(PersonNameLink personNameLink) {
        try {
            return personNameLinkRepository.save(personNameLink);
        } catch (DataIntegrityViolationException e) {
            Optional<PersonNameLink> storedPnl = personNameLinkRepository.findByPrivatePersonIdAndPersonNameIdAndSource(
                personNameLink.getPrivatePerson().getId(),
                personNameLink.getPersonName().getId(),
                personNameLink.getSource()
            );
            return storedPnl.orElse(null);
        }
        // Other exceptions can be caught and handled as well
    }
}
