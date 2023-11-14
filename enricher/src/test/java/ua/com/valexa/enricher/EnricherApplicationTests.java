package ua.com.valexa.enricher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import ua.com.valexa.db.model.data.base_objects.PrivatePerson;
import ua.com.valexa.db.model.stage.PrivatePersonStageRow;
import ua.com.valexa.db.repository.data.base_objects.PrivatePersonRepository;
import ua.com.valexa.db.repository.stage.PrivatePersonRowStageRepository;
import ua.com.valexa.enricher.service.PrivatePersonStageService;

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

}
