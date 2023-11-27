package ua.com.valexa.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;
import ua.com.valexa.db.repository.data.PersonNameLinkRepository;

@Service
public class PersonNameLinkService {

    @Autowired
    PersonNameLinkRepository personNameLinkRepository;

    public PersonNameLink save(PersonNameLink personNameLink){
        try {
            return personNameLinkRepository.save(personNameLink);
        } catch (DataIntegrityViolationException ex){
            return personNameLink;
        }
    }

}
