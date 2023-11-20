package ua.com.valexa.enricher.service;

import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.base_object.PrivatePerson;
import ua.com.valexa.db.repository.data.base_object.PrivatePersonRepository;
import ua.com.valexa.db.service.data.base_object.PrivatePersonService;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class TestService {

    @Autowired
    PrivatePersonService privatePersonService;


    @Autowired
    PrivatePersonRepository privatePersonRepository;


//    @Transactional
    public void tst() {
        PrivatePerson p = privatePersonRepository.findById(UUID.fromString("bd811930-2624-4083-99ea-56f0709eb2ae")).orElse(null);
//        Hibernate.initialize(p.getPersonNameLinks());
        System.out.println(p);

    }

}
