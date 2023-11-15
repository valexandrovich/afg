package ua.com.valexa.db.service.data.attribute.local_passport;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.address.AddressPersonLink;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;
import ua.com.valexa.db.model.data.attribute.local_passport.LocalPassportLink;
import ua.com.valexa.db.repository.data.attribute.inn.InnLinkRepository;
import ua.com.valexa.db.repository.data.attribute.local_passport.LocalPassportLinkRepository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class LocalPassportLinkService {
    @Autowired
    LocalPassportLinkRepository localPassportLinkRepository;

    public LocalPassportLink save(LocalPassportLink localPassportLink) {
        try {
            return localPassportLinkRepository.save(localPassportLink);
        } catch (DataIntegrityViolationException e) {
            Optional<LocalPassportLink> stored = localPassportLinkRepository.findByPrivatePersonIdAndLocalPassportIdAndSource(
                    localPassportLink.getPrivatePerson().getId(),
                    localPassportLink.getLocalPassport().getId(),
                    localPassportLink.getSource()
            );
            return stored.orElse(null);
        }
        // Other exceptions can be caught and handled as well
    }


    @Async
    @Transactional
    public CompletableFuture<Void> saveAsync(LocalPassportLink localPassportLink){
        return CompletableFuture.runAsync(()->{
            localPassportLinkRepository.save(localPassportLink);
        });
    }
}
