package ua.com.valexa.db.service.data.attribute.inn;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.birthday.BirthdayPersonLink;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;
import ua.com.valexa.db.repository.data.attribute.inn.InnLinkRepository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class InnLinkService {
    @Autowired
    InnLinkRepository innLinkRepository;

    public InnLink save(InnLink innLink) {
        try {
            return innLinkRepository.save(innLink);
        } catch (DataIntegrityViolationException e) {
            Optional<InnLink> storedIl = innLinkRepository.findByPrivatePersonIdAndInnIdAndSource(
                    innLink.getPrivatePerson().getId(),
                    innLink.getInn().getId(),
                    innLink.getSource()
            );
            return storedIl.orElse(null);
        }
        // Other exceptions can be caught and handled as well
    }

    public InnLink save2(InnLink innLink) {
        return innLinkRepository.save(innLink);
    }

    @Async
    @Transactional
    public CompletableFuture<Void> saveAsync(InnLink innLink){
        return CompletableFuture.runAsync(()->{
            innLinkRepository.save(innLink);
        });
    }
}
