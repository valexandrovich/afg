package ua.com.valexa.enricher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.valexa.db.model.stage.PrivatePersonStageRow;
import ua.com.valexa.db.model.stage.PrivatePersonStageRowArchive;
import ua.com.valexa.db.repository.stage.PrivatePersonStageRowArchiveRepository;
import ua.com.valexa.db.repository.stage.PrivatePersonStageRowRepository;
import ua.com.valexa.db.service.data.base_object.PrivatePersonService;
import ua.com.valexa.enricher.service.PrivatePersonStageService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pp")
public class PrivatePersonController {

    @Autowired
    PrivatePersonStageRowArchiveRepository privatePersonStageRowArchiveRepository;

    @Autowired
    PrivatePersonStageRowRepository privatePersonStageRowRepository;

    @Autowired
    PrivatePersonService privatePersonService;

    @Autowired
    PrivatePersonStageService privatePersonStageService;

    @PostMapping("/all")
    public void enrichAllStage() {
        int batchSize = 1000;

        for (int i = 1; i <= 1000; i++) {

            long startTime = System.currentTimeMillis();
            tst2(batchSize);
            long endTime = System.currentTimeMillis();
            long elapsedTimeMillis = endTime - startTime;

            // Convert milliseconds to minutes, seconds, and milliseconds
            long minutes = (elapsedTimeMillis / 1000) / 60;
            long seconds = (elapsedTimeMillis / 1000) % 60;
            long milliseconds = elapsedTimeMillis % 1000;
            System.out.printf("I( " + (i * batchSize) + " ): " + i + " :   %02d:%02d:%03d\n", minutes, seconds, milliseconds);

        }
    }

    void tst2(Integer batchSize) {

        long startTime = System.currentTimeMillis();
        Slice<PrivatePersonStageRow> rows = privatePersonStageRowRepository.findBy(PageRequest.of(0, batchSize));
        long endTime = System.currentTimeMillis();
        long elapsedTimeMillis = endTime - startTime;
        long minutes = (elapsedTimeMillis / 1000) / 60;
        long seconds = (elapsedTimeMillis / 1000) % 60;
        long milliseconds = elapsedTimeMillis % 1000;
        System.out.printf("FETCHING STAGE" + " :   %02d:%02d:%03d\n", minutes, seconds, milliseconds);


        if (rows.hasContent()) {

            List<PrivatePersonStageRowArchive> archiveRows = new ArrayList<>();

            startTime = System.currentTimeMillis();
            privatePersonStageService.enrichRows(rows.getContent());
            endTime = System.currentTimeMillis();
            elapsedTimeMillis = endTime - startTime;
            minutes = (elapsedTimeMillis / 1000) / 60;
            seconds = (elapsedTimeMillis / 1000) % 60;
            milliseconds = elapsedTimeMillis % 1000;
            System.out.printf("ENRICH ROWS" + " :   %02d:%02d:%03d\n", minutes, seconds, milliseconds);

            for (PrivatePersonStageRow row : rows.getContent()) {
                archiveRows.add(new PrivatePersonStageRowArchive(row));
            }
            startTime = System.currentTimeMillis();
            privatePersonStageRowArchiveRepository.saveAll(archiveRows);
            endTime = System.currentTimeMillis();
            elapsedTimeMillis = endTime - startTime;
            minutes = (elapsedTimeMillis / 1000) / 60;
            seconds = (elapsedTimeMillis / 1000) % 60;
            milliseconds = elapsedTimeMillis % 1000;
            System.out.printf("SAVE ARCHIVE ROWS" + " :   %02d:%02d:%03d\n", minutes, seconds, milliseconds);

            startTime = System.currentTimeMillis();
            privatePersonStageRowRepository.deleteAll(rows.getContent());
            endTime = System.currentTimeMillis();
            elapsedTimeMillis = endTime - startTime;
            minutes = (elapsedTimeMillis / 1000) / 60;
            seconds = (elapsedTimeMillis / 1000) % 60;
            milliseconds = elapsedTimeMillis % 1000;
            System.out.printf("DELETE STAGE ROWS" + " :   %02d:%02d:%03d\n", minutes, seconds, milliseconds);


        }


    }


}
