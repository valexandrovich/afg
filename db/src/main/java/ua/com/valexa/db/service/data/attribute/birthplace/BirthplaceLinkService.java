package ua.com.valexa.db.service.data.attribute.birthplace;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.birthplace.BithplaceLink;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;
import ua.com.valexa.db.repository.data.attribute.birthplace.BithplaceLinkRepository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class BirthplaceLinkService {
    @Autowired
    BithplaceLinkRepository bithplaceLinkRepository;

    public BithplaceLink save(BithplaceLink bithplaceLink) {
        try {
            return bithplaceLinkRepository.save(bithplaceLink);
        } catch (DataIntegrityViolationException e) {
            Optional<BithplaceLink> stored = bithplaceLinkRepository.findByPrivatePersonIdAndBirthplaceIdAndSource(
                    bithplaceLink.getPrivatePerson().getId(),
                    bithplaceLink.getBirthplace().getId(),
                    bithplaceLink.getSource()
            );
            return stored.orElse(null);
        }
        // Other exceptions can be caught and handled as well
    }

    @Async
    @Transactional
    public CompletableFuture<Void> saveAsync(BithplaceLink bithplaceLink){
        return CompletableFuture.runAsync(()->{
//            long startTime = System.currentTimeMillis();
            bithplaceLinkRepository.save(bithplaceLink);
//            long endTime = System.currentTimeMillis(); // Get end time
//            long duration = endTime - startTime;
//            long minutes = duration / 60000;
//            long seconds = (duration % 60000) / 1000;
//            long milliseconds = duration % 1000;
//            System.out.printf("saveAsync(BithplaceLink bithplaceLink): " + "%02d:%02d:%03d%n", minutes, seconds, milliseconds);
        });
    }
}
