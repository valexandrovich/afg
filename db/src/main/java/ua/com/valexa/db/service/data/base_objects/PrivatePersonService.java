package ua.com.valexa.db.service.data.base_objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;
import ua.com.valexa.db.model.data.base_objects.PrivatePerson;
import ua.com.valexa.db.repository.data.base_objects.PrivatePersonRepository;
import ua.com.valexa.db.service.data.attribute.person_name.PersonNameService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PrivatePersonService {


    @Autowired
    PersonNameService personNameService;

    @Autowired
    PrivatePersonRepository privatePersonRepository;

    public Set<PrivatePerson> findPersonsByNoVowelsHash(String hash) {
        return personNameService.findByNoVowelsHash(hash)
                .stream().flatMap(personName -> personName.getNameLinks().stream()
                        .map(PersonNameLink::getPrivatePerson))
                .collect(Collectors.toSet());
    }

    public Set<PrivatePerson> findPersonsByName(
            String lastName,
            String firstName,
            String patronymicName
    ) {
        return personNameService.findByName(lastName, firstName, patronymicName)
                .stream().flatMap(personName -> personName.getNameLinks().stream()
                        .map(PersonNameLink::getPrivatePerson))
                .collect(Collectors.toSet());
    }

    public PrivatePerson save(PrivatePerson privatePerson){
        return privatePersonRepository.save(privatePerson);
    }

}
