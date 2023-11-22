package ua.com.valexa.db.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.PersonNameLink;

import java.util.UUID;

@Repository
public interface PersonNameLinkRepository extends JpaRepository<PersonNameLink, UUID> {
}
