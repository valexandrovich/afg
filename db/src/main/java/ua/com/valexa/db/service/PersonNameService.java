package ua.com.valexa.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;
import ua.com.valexa.db.repository.data.PersonNameRepository;

@Service
public class PersonNameService {


    @Autowired
    PersonNameRepository personNameRepository;

    public PersonName save(PersonName personName){
        try {
            return personNameRepository.save(personName);
        } catch (DataIntegrityViolationException ex){
            return personName;
        }
    }

}
