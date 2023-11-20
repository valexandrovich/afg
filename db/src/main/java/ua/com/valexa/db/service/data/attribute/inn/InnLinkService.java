package ua.com.valexa.db.service.data.attribute.inn;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;
import ua.com.valexa.db.repository.data.attribute.inn.InnLinkRepository;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class InnLinkService {

    @Autowired
    InnLinkRepository innLinkRepository;

    @Async
    @Transactional
    public CompletableFuture<Void> saveAll(Set<InnLink> innLinks){
        return CompletableFuture.runAsync(()->{
            innLinkRepository.saveAll(innLinks);
        });
    }
    @Async
    @Transactional
    public CompletableFuture<Void> save(InnLink innLink){
        return CompletableFuture.runAsync(()->{
            innLinkRepository.save(innLink);
        });
    }

}
