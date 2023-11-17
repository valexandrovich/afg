package ua.com.valexa.enricher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import ua.com.valexa.db.model.data.attribute.address_simple.AddressSimple;
import ua.com.valexa.db.model.data.attribute.address_simple.AddressSimplePersonLink;
import ua.com.valexa.db.model.data.base_objects.PrivatePerson;
import ua.com.valexa.db.model.stage.PrivatePersonStageRow;
import ua.com.valexa.db.model.stage.PrivatePersonStageRowArchive;
import ua.com.valexa.db.repository.data.attribute.address_simple.AddressSimplePersonLinkRepository;
import ua.com.valexa.db.repository.data.attribute.birthday.BirthdayPersonLinkRepository;
import ua.com.valexa.db.repository.data.attribute.person_name.PersonNameLinkRepository;
import ua.com.valexa.db.repository.data.attribute.person_name.PersonNameRepository;
import ua.com.valexa.db.repository.data.base_objects.PrivatePersonRepository;
import ua.com.valexa.db.repository.stage.PrivatePersonStageRowArchiveRepository;
import ua.com.valexa.db.repository.stage.PrivatePersonStageRowRepository;
import ua.com.valexa.enricher.service.PrivatePersonStageService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class EnricherApplicationTests {

    @Autowired
    PrivatePersonStageRowRepository privatePersonStageRowRepository;

    @Autowired
    PrivatePersonStageService privatePersonStageService;


    @Test
    void tstLoop() {

        int batchSize = 1000;

        for (int i = 1; i <= 2; i++) {
//            if (i % 30 == 0){

//            }

            long startTime = System.currentTimeMillis();
            contextLoads(batchSize);
            long endTime = System.currentTimeMillis();
            long elapsedTimeMillis = endTime - startTime;

            // Convert milliseconds to minutes, seconds, and milliseconds
            long minutes = (elapsedTimeMillis / 1000) / 60;
            long seconds = (elapsedTimeMillis / 1000) % 60;
            long milliseconds = elapsedTimeMillis % 1000;
            System.out.printf("I( " + (i * batchSize) + " ): " + i + " :   %02d:%02d:%03d\n", minutes, seconds, milliseconds);
//            System.out.println("I (k): "+ i);

        }
    }

    @Autowired
    PrivatePersonStageRowArchiveRepository privatePersonStageRowArchiveRepository;

    @Test
    void contextLoads(int batchSize) {
//
//        Page<PrivatePersonStageRow> page = privatePersonRowStageRepository.findAllByIsHandledPage(
//                PageRequest.of(0, 1)
//        );
//
        long startTime = System.currentTimeMillis();
//        Slice<PrivatePersonStageRow> batch = privatePersonStageRowRepository.findAllByIsHandled(false, PageRequest.of(0, batchSize));
//        Slice<PrivatePersonStageRow> batch = privatePersonStageRowRepository.findAll(PageRequest.of(0, batchSize));
        Slice<PrivatePersonStageRow> batch = privatePersonStageRowRepository.findBy(PageRequest.of(0, batchSize));



        long endTime = System.currentTimeMillis(); // Get end time
        long duration = endTime - startTime;
        long minutes = duration / 60000;
        long seconds = (duration % 60000) / 1000;
        long milliseconds = duration % 1000;
        String t1 = String.format("Fetching rows: " + "%02d:%02d:%03d%n", minutes, seconds, milliseconds);

//            PrivatePersonStageRow row = batch.getContent().get(0);
//        privatePersonStageService.enrichStageRow(row);
//        row.setHandled(true);
//        privatePersonRowStageRepository.save(row);
        List<PrivatePersonStageRowArchive> rowsToArchive = new ArrayList<>();

        startTime = System.currentTimeMillis();
        for (PrivatePersonStageRow row : batch.getContent()) {

//        System.out.println(row);
//            System.out.println(row.getLastNameUa());
//
            privatePersonStageService.enrichStageRow(row);


//            row.setHandled(true);

//            startTime = System.currentTimeMillis();
            rowsToArchive.add(new PrivatePersonStageRowArchive(row));
//            PrivatePersonStageRowArchive arch = new PrivatePersonStageRowArchive(row);
//            endTime = System.currentTimeMillis(); // Get end time
//            duration = endTime - startTime;
//            minutes = duration / 60000;
//            seconds = (duration % 60000) / 1000;
//            milliseconds = duration % 1000;
//            System.out.printf("PrivatePersonStageRowArchive arch = new PrivatePersonStageRowArchive(row);: " + "%02d:%02d:%03d%n", minutes, seconds, milliseconds);


//            startTime = System.currentTimeMillis();
//            privatePersonStageRowArchiveRepository.save(arch);
//            endTime = System.currentTimeMillis(); // Get end time
//            duration = endTime - startTime;
//            minutes = duration / 60000;
//            seconds = (duration % 60000) / 1000;
//            milliseconds = duration % 1000;
//            System.out.printf("privatePersonStageRowArchiveRepository.save(arch);: " + "%02d:%02d:%03d%n", minutes, seconds, milliseconds);


//            startTime = System.currentTimeMillis();
//            privatePersonStageRowRepository.delete(row);
//            endTime = System.currentTimeMillis(); // Get end time
//            duration = endTime - startTime;
//            minutes = duration / 60000;
//            seconds = (duration % 60000) / 1000;
//            milliseconds = duration % 1000;
//            System.out.printf(" privatePersonStageRowRepository.delete(row);;: " + "%02d:%02d:%03d%n", minutes, seconds, milliseconds);

//            privatePersonStageRowRepository.save(row);
        }
        endTime = System.currentTimeMillis(); // Get end time
        duration = endTime - startTime;
        minutes = duration / 60000;
        seconds = (duration % 60000) / 1000;
        milliseconds = duration % 1000;
        String t2 = String.format("Rows enrichment: " + "%02d:%02d:%03d%n", minutes, seconds, milliseconds);


        startTime = System.currentTimeMillis();
        privatePersonStageRowArchiveRepository.saveAll(rowsToArchive);
        endTime = System.currentTimeMillis(); // Get end time
        duration = endTime - startTime;
        minutes = duration / 60000;
        seconds = (duration % 60000) / 1000;
        milliseconds = duration % 1000;
        String t3 = String.format("Moving to archive: " + "%02d:%02d:%03d%n", minutes, seconds, milliseconds);

        startTime = System.currentTimeMillis();
        privatePersonStageRowRepository.deleteAll(batch.getContent());
        endTime = System.currentTimeMillis(); // Get end time
        duration = endTime - startTime;
        minutes = duration / 60000;
        seconds = (duration % 60000) / 1000;
        milliseconds = duration % 1000;
        String t4 = String.format("Deleting stage: " + "%02d:%02d:%03d%n", minutes, seconds, milliseconds);


        System.out.print(t1 + "; " +  t2 + "; " +  t3 + "; " +  t4);

    }

    @Autowired
    PrivatePersonRepository privatePersonRepository;

    @Test
    void getP() {
        Optional<PrivatePerson> p = privatePersonRepository.findById(UUID.fromString("78adf6e9-8b7b-41ca-a345-5c2339605cb8"));
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
    void tst2() {

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


    @Test
    void tst3() {

        privatePersonStageService.test();

    }


}
