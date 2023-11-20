package ua.com.valexa.db.repository.data.attribute.birthday;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.birthday.BirthdayLink;

import java.util.UUID;

@Repository
public interface BirthdayLinkRepository extends JpaRepository<BirthdayLink, UUID> {
}
