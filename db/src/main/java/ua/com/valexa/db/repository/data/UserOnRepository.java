package ua.com.valexa.db.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.UserOn;

import java.util.UUID;

@Repository
public interface UserOnRepository extends JpaRepository<UserOn, UUID> {
}
