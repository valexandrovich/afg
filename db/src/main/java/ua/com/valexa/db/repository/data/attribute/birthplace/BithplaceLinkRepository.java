package ua.com.valexa.db.repository.data.attribute.birthplace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.birthplace.BithplaceLink;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BithplaceLinkRepository extends JpaRepository<BithplaceLink, UUID> {
    Optional<BithplaceLink> findByPrivatePersonIdAndBirthplaceIdAndSource(
            UUID privatePersonId,
            UUID birthplaceId,
            String source
    );
}
