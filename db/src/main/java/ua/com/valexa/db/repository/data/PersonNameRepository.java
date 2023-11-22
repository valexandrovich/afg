package ua.com.valexa.db.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.PersonName;
import ua.com.valexa.db.model.enums.LanguageCode;

import java.util.UUID;

@Repository
public interface PersonNameRepository extends JpaRepository<PersonName, UUID> {

    PersonName findByLastAndFirstAndPatronymicAndLanguageCode(
            String last,
            String first,
            String patronymic,
            LanguageCode languageCode
    );

}
