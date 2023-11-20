package ua.com.valexa.db.repository.stage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.stage.PrivatePersonStageRowArchive;

import java.util.UUID;

@Repository
public interface PrivatePersonStageRowArchiveRepository extends JpaRepository<PrivatePersonStageRowArchive, UUID> {


}
