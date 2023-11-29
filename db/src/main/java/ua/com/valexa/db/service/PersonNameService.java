package ua.com.valexa.db.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;
import ua.com.valexa.db.repository.data.PersonNameRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonNameService {


    @Autowired
    PersonNameRepository personNameRepository;

    @Autowired
    EntityManager em;

    @Transactional
    public void saveAll(Set<PersonName> personNames) {

        Set<UUID> ids = personNames.stream()
                .map(PersonName::getId)
                .collect(Collectors.toSet());

        List<PersonName> exsistingEntities = em.createQuery(
                        "SELECT pn FROM PersonName  pn WHERE pn.id IN :ids", PersonName.class
                )
                .setParameter("ids", ids)
                .getResultList();

        Set<UUID> existingIds = exsistingEntities.stream()
                .map(PersonName::getId)
                .collect(Collectors.toSet());

        Set<PersonName> newEntities = personNames.stream()
                .filter(e -> !existingIds.contains(e.getId()))
                .collect(Collectors.toSet());

        Set<PersonName> existedEntities = personNames.stream()
                .filter(e -> existingIds.contains(e.getId()))
                .collect(Collectors.toSet());


        System.out.println("Existed: " + existedEntities.size());
        System.out.println("New: " + newEntities.size());

        for (PersonName pn : newEntities){
            em.persist(pn);
        }

        for (PersonName pn : existedEntities){
            em.merge(pn);
        }

        em.flush();


    }

}
