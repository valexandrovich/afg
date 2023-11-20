package ua.com.valexa.db.repository.data.attribute.person_name;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;

import java.util.Set;
import java.util.UUID;

@Repository
public interface PersonNameRepository extends JpaRepository<PersonName, UUID> {

    Set<PersonName> findByNoVowelsHashIn(Set<String> noVowelsHashes);

}
