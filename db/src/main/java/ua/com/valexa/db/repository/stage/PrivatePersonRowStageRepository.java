package ua.com.valexa.db.repository.stage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.stage.PrivatePersonStageRow;

import java.util.UUID;

@Repository
public interface PrivatePersonRowStageRepository extends JpaRepository<PrivatePersonStageRow, UUID> {

    Slice<PrivatePersonStageRow> findAllByIsHandled(Boolean isHandled , Pageable pageable);
//    Page<PrivatePersonStageRow> findAllByIsHandled(Boolean isHandled, Pageable pageable);

}
