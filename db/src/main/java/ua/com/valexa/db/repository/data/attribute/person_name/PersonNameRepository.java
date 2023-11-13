package ua.com.valexa.db.repository.data.attribute.person_name;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.enums.LanguageCode;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface PersonNameRepository extends JpaRepository<PersonName, UUID> {

    Optional<PersonName> findByLastNameAndFirstNameAndPatronymicNameAndLanguageCode(
            String lastName,
            String firstName,
            String patronymicName,
            LanguageCode languageCode
    );

    Set<PersonName> findByLastNameAndFirstNameAndPatronymicName(
            String lastName,
            String firstName,
            String patronymicName
    );

    Set<PersonName> findByNoVowelsHash(String noVowelsHash);

}
