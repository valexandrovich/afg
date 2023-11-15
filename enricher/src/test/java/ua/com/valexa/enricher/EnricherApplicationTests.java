package ua.com.valexa.enricher;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import ua.com.valexa.db.model.data.attribute.address_simple.AddressSimple;
import ua.com.valexa.db.model.data.attribute.address_simple.AddressSimplePersonLink;
import ua.com.valexa.db.model.data.attribute.birthday.Birthday;
import ua.com.valexa.db.model.data.attribute.birthday.BirthdayPersonLink;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;
import ua.com.valexa.db.model.data.base_objects.PrivatePerson;
import ua.com.valexa.db.model.data.enums.LanguageCode;
import ua.com.valexa.db.model.stage.PrivatePersonStageRow;
import ua.com.valexa.db.repository.data.attribute.address_simple.AddressSimplePersonLinkRepository;
import ua.com.valexa.db.repository.data.attribute.birthday.BirthdayPersonLinkRepository;
import ua.com.valexa.db.repository.data.attribute.person_name.PersonNameLinkRepository;
import ua.com.valexa.db.repository.data.attribute.person_name.PersonNameRepository;
import ua.com.valexa.db.repository.data.base_objects.PrivatePersonRepository;
import ua.com.valexa.db.repository.stage.PrivatePersonRowStageRepository;
import ua.com.valexa.enricher.service.PrivatePersonStageService;

import java.time.LocalDate;
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
        for (int i = 0; i < 1000000; i++) {
            if (i % 30 == 0){
                System.out.println("I: "+ i);
            }

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

    @Autowired
    BirthdayPersonLinkRepository birthdayPersonLinkRepository;


    @Autowired
    AddressSimplePersonLinkRepository addressSimplePersonLinkRepository;



    @Test
    void tst2(){

        PrivatePerson p = new PrivatePerson();
//        p.setId(UUID.randomUUID());
        p.setId(UUID.fromString("b249c81e-5c03-4bf2-85cc-7af13eedb8c3"));

        AddressSimple a = new AddressSimple();
        a.setAddress("asdadad");
        a.generateId();

        AddressSimplePersonLink aspl1 = new AddressSimplePersonLink();
        aspl1.setAddressSimple(a);
        aspl1.setPrivatePerson(p);
        aspl1.setCreatedAt(LocalDateTime.now());
        aspl1.setSource("UNIT");
        aspl1.generateId();

        AddressSimplePersonLink aspl2 = new AddressSimplePersonLink();
        aspl2.setAddressSimple(a);
        aspl2.setPrivatePerson(p);
        aspl2.setCreatedAt(LocalDateTime.now());
        aspl2.setSource("UNIT");
        aspl2.generateId();

        System.out.println("");

        addressSimplePersonLinkRepository.save(aspl1);
        addressSimplePersonLinkRepository.save(aspl2);







    }

}
