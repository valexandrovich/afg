package ua.com.valexa.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.tag.TagAttributeLink;

import java.util.UUID;

@Repository
public interface TagAttributeLinkRepository extends JpaRepository<TagAttributeLink, UUID> {
}
