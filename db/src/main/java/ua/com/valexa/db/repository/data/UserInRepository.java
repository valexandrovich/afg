package ua.com.valexa.db.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.UserIn;

import java.util.UUID;

@Repository
public interface UserInRepository extends JpaRepository<UserIn, UUID> {
}
