package ua.com.valexa.enricher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;
import ua.com.valexa.db.model.data.base_objects.PrivatePerson;
import ua.com.valexa.db.model.data.enums.LanguageCode;
import ua.com.valexa.db.model.stage.PrivatePersonStageRow;
import ua.com.valexa.db.repository.data.attribute.person_name.PersonNameLinkRepository;
import ua.com.valexa.db.repository.data.attribute.person_name.PersonNameRepository;
import ua.com.valexa.db.repository.data.base_objects.PrivatePersonRepository;
import ua.com.valexa.db.repository.stage.PrivatePersonRowStageRepository;
import ua.com.valexa.enricher.service.PrivatePersonStageService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class EnricherApplicationTests {

    @Autowired
    PrivatePersonRowStageRepository privatePersonRowStageRepository;

    @Autowired
    PrivatePersonStageService privatePersonStageService;


    @Test
    void tstLoop(){
        for (int i = 0; i < 1000; i++) {
            System.out.println("I: "+ i);
            contextLoads();
        }
    }
    @Test
    void contextLoads() {
        Slice<PrivatePersonStageRow> batch = privatePersonRowStageRepository.findAllByIsHandled(false, PageRequest.of(0, 1));
        if (batch.isEmpty()){
            return;
        }
        PrivatePersonStageRow row = batch.getContent().get(0);
//        System.out.println(row);
        privatePersonStageService.enrichStageRow(row);
        row.setHandled(true);
        privatePersonRowStageRepository.save(row);



    }

    @Autowired
    PrivatePersonRepository privatePersonRepository;

    @Test
    void getP(){
        Optional<PrivatePerson> p =  privatePersonRepository.findById(UUID.fromString("78adf6e9-8b7b-41ca-a345-5c2339605cb8"));
        System.out.println(p);
    }

//    @Autowired
//    PrivatePersonRepository privatePersonRepository;

    @Autowired
    PersonNameRepository personNameRepository;

    @Autowired
    PersonNameLinkRepository personNameLinkRepository;

    @Test
    void tst2(){

        PrivatePerson p = new PrivatePerson();
//        p.setId(UUID.randomUUID());
        p.setId(UUID.fromString("b249c81e-5c03-4bf2-85cc-7af13eedb8c3"));

        PersonName pn = new PersonName();
        pn.setLastName("BIELOIENKO");
        pn.setFirstName("VALERII");
        pn.setLanguageCode(LanguageCode.EN);
        pn.generateNoVolwesHash();
        pn.generateId();
        PersonName pn2 = new PersonName();
        pn2.setLastName("BIELOIENKO");
        pn2.setFirstName("VALERII");
        pn2.setLanguageCode(LanguageCode.EN);
        pn2.generateNoVolwesHash();
        pn2.generateId();

        PersonNameLink pnl = new PersonNameLink();
        pnl.setSource("UNIT");
        pnl.setCreatedAt(LocalDateTime.now());
        pnl.setPrivatePerson(p);
        pnl.setPersonName(pn);
        pnl.generateId();
        System.out.println("PNL BEFORE SAVE");
        System.out.println(pnl);
        System.out.println(pnl.toString());
        System.out.println(Arrays.toString(pnl.toString().getBytes()));
        System.out.println(UUID.nameUUIDFromBytes(pnl.toString().getBytes()));
        System.out.println(pnl.getId());
        personNameLinkRepository.save(pnl);
        System.out.println("PNL AFTER SAVE");
        System.out.println(pnl);
        System.out.println(pnl.toString());
        System.out.println(Arrays.toString(pnl.toString().getBytes()));
        System.out.println(UUID.nameUUIDFromBytes(pnl.toString().getBytes()));
        System.out.println(pnl.getId());


        System.out.println("--------------------------------");
        PersonNameLink pnl2 = new PersonNameLink();
        pnl2.setSource("UNIT2");
        pnl2.setCreatedAt(LocalDateTime.now());
        pnl2.setPrivatePerson(p);
        pnl2.setPersonName(pn);
        pnl2.generateId();
        System.out.println("PNL2 BEFORE SAVE");
        System.out.println(pnl2);
        System.out.println(pnl2.toString());
        System.out.println(Arrays.toString(pnl2.toString().getBytes()));
        System.out.println(UUID.nameUUIDFromBytes(pnl2.toString().getBytes()));
        System.out.println(pnl2.getId());

        personNameLinkRepository.save(pnl2);
        System.out.println("PNL2 AFTER SAVE");
        System.out.println(pnl2);
        System.out.println(pnl2.toString());
        System.out.println(Arrays.toString(pnl2.toString().getBytes()));
        System.out.println(UUID.nameUUIDFromBytes(pnl2.toString().getBytes()));
        System.out.println(pnl2.getId());
        System.out.println("s");




    }

}
