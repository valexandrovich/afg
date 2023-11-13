package ua.com.valexa.db.service.data.attribute.person_name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;
import ua.com.valexa.db.repository.data.attribute.person_name.PersonNameRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class PersonNameService {

    @Autowired
    PersonNameRepository personNameRepository;

    public Set<PersonName> findByNoVowelsHash(String noVowelsHash){
        return personNameRepository.findByNoVowelsHash(noVowelsHash);
    }

    public Set<PersonName> findByName(
            String lastName,
            String firstName,
            String patronymicName
    ) {
        return personNameRepository.findByLastNameAndFirstNameAndPatronymicName(
                lastName, firstName, patronymicName
        );
    }


    public PersonName savePersonName(PersonName personName) {
        try {
            return personNameRepository.save(personName);
        } catch (DataIntegrityViolationException e) {
            Optional<PersonName> storedPn = personNameRepository.findByLastNameAndFirstNameAndPatronymicNameAndLanguageCode(
                    personName.getLastName(),
                    personName.getFirstName(),
                    personName.getPatronymicName(),
                    personName.getLanguageCode()
            );
            return storedPn.orElse(null);
        }
        // Other exceptions can be caught and handled as well
    }

}
