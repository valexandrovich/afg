package ua.com.valexa.db.repository.data.attribute.birthday;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.birthday.Birthday;

import java.util.UUID;

@Repository
public interface BirthdayRepository extends JpaRepository<Birthday, UUID> {
}
