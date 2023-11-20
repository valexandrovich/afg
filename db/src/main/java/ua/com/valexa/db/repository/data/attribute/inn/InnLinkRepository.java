package ua.com.valexa.db.repository.data.attribute.inn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;

import java.util.UUID;

@Repository
public interface InnLinkRepository extends JpaRepository<InnLink, UUID> {
}
