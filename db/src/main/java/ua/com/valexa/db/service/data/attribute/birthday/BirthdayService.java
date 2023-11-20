package ua.com.valexa.db.service.data.attribute.birthday;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.birthday.Birthday;
import ua.com.valexa.db.repository.data.attribute.birthday.BirthdayRepository;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class BirthdayService {
    @Autowired
    BirthdayRepository birthdayRepository;

    @Async
    @Transactional
    public CompletableFuture<Void> saveAll(Set<Birthday> birthdays){
        return CompletableFuture.runAsync(()->{
            birthdayRepository.saveAll(birthdays);
        });
    }
}
