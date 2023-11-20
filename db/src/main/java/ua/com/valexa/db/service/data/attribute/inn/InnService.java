package ua.com.valexa.db.service.data.attribute.inn;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.inn.Inn;
import ua.com.valexa.db.repository.data.attribute.inn.InnRepository;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class InnService {

    @Autowired
    InnRepository innRepository;

    @Async
    @Transactional
    public CompletableFuture<Void> saveAll(Set<Inn> inns){
        return CompletableFuture.runAsync(()->{
            innRepository.saveAll(inns);
        });
    }
    @Async
    @Transactional
    public CompletableFuture<Void> save(Inn inn){
        return CompletableFuture.runAsync(()->{
            innRepository.save(inn);
        });
    }

}
