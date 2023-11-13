package ua.com.valexa.db.repository.data.attribute.local_passport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;
import ua.com.valexa.db.model.data.attribute.local_passport.LocalPassportLink;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocalPassportLinkRepository extends JpaRepository<LocalPassportLink, UUID> {
    Optional<LocalPassportLink> findByPrivatePersonIdAndLocalPassportIdAndSource(
            UUID privatePersonId,
            UUID localPassportId,
            String source
    );
}
