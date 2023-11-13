package ua.com.valexa.db.repository.data.attribute.birthday;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.birthday.BirthdayPersonLink;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonBirthdayLinkRepository extends JpaRepository<BirthdayPersonLink, UUID> {

    Optional<BirthdayPersonLink> findByPrivatePersonIdAndBirthdayIdAndSource(
            UUID privatePersonId,
            UUID birthdayId,
            String source
    );
}
