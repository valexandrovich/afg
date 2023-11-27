package ua.com.valexa.db.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.inn.Inn;

import java.util.UUID;

@Repository
public interface InnRepository extends JpaRepository<Inn, UUID> {
}
