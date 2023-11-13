package ua.com.valexa.db.repository.data.attribute.person_name;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonNameLinkRepository extends JpaRepository<PersonNameLink, UUID> {
    Optional<PersonNameLink> findByPrivatePersonIdAndPersonNameIdAndSource(
            UUID privatePersonId,
            UUID personNameId,
            String source
    );
}
