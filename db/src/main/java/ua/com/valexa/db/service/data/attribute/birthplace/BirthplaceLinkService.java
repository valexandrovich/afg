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
            bithplaceLinkRepository.save(bithplaceLink);
        });
    }
}
