package ua.com.valexa.enricher;

import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.valexa.db.model.data.PersonName;
import ua.com.valexa.db.model.data.PersonNameLink;
import ua.com.valexa.db.model.data.PrivatePerson;
import ua.com.valexa.db.model.enums.LanguageCode;
import ua.com.valexa.db.repository.data.PersonNameLinkRepository;
import ua.com.valexa.db.repository.data.PersonNameRepository;
import ua.com.valexa.db.repository.data.PrivatePersonRepository;
import ua.com.valexa.db.service.data.PersonNameService;

import java.util.UUID;

import static ua.com.valexa.db.model.enums.LanguageCode.UA;

@SpringBootTest
class EnricherApplicationTests {

    @Autowired
    PrivatePersonRepository privatePersonRepository;


    @Autowired
    PersonNameRepository personNameRepository;

    @Autowired
    PersonNameLinkRepository personNameLinkRepository;

    @Autowired
    PersonNameService personNameService;

    @Test
    void tst1(){
        PrivatePerson p = new PrivatePerson();
        p = privatePersonRepository.save(p);


        PersonName pn = new PersonName();
        pn.setLast("BIELOIENKO");
        pn.setFirst("VALERII");
        pn.setPatronymic("ALEXANDROVICH");
        pn.setLanguageCode(UA);
        pn = personNameRepository.save(pn);


        PersonNameLink pnl = new PersonNameLink();
        pnl.setPrivatePerson(p);
        pnl.setPersonName(pn);
        pnl = personNameLinkRepository.save(pnl);

        PersonName pn2 = new PersonName();
        pn2.setLast("BIELOIENKO");
        pn2.setFirst("VALERII");
        pn2.setPatronymic("ALEXANDROVICH");
        pn2.setLanguageCode(UA);
        pn2 = personNameRepository.save(pn2);


        PersonNameLink pnl2 = new PersonNameLink();
        pnl2.setPrivatePerson(p);
        pnl2.setPersonName(pn2);
        pnl2 = personNameLinkRepository.save(pnl2);

    }

    @Test
    @Transactional
    void tst2(){
        PrivatePerson p = privatePersonRepository.findById(UUID.fromString("7bb5d863-aa07-4e18-a721-b24fe2d16660")).orElse(null);
        System.out.println("d");
    }


    @Test
    void tst3(){
            PersonName pn = new PersonName();
            pn.setLast("BIELOIENKOS");
            pn.setLanguageCode(UA);
            pn.setSomeField("XXX");
//            pn.generateId();
            pn =  personNameService.save(pn);

        System.out.println("ok");
    }


}
