package ua.com.valexa.enricher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import ua.com.valexa.db.model.stage.PrivatePersonStageRow;
import ua.com.valexa.db.model.stage.PrivatePersonStageRowArchive;
import ua.com.valexa.db.repository.stage.PrivatePersonStageRowArchiveRepository;
import ua.com.valexa.db.repository.stage.PrivatePersonStageRowRepository;
import ua.com.valexa.enricher.service.PrivatePersonStageService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pp")
public class PrivatePersonController {

    @Autowired
    PrivatePersonStageService privatePersonStageService;

    @Autowired
    PrivatePersonStageRowRepository privatePersonStageRowRepository;

    @Autowired
    PrivatePersonStageRowArchiveRepository privatePersonStageRowArchiveRepository;

    @PostMapping("/stage/{id}")
    public void enrichPrivatePersonStage(@PathVariable UUID id) {
        privatePersonStageService.enrichStageRow(id);
    }

    @PostMapping("/stage")
    public void enrichPrivatePersonStageRow(
            @RequestBody PrivatePersonStageRow row
    ) {
        privatePersonStageService.enrichStageRow(row);
    }

    @PostMapping("/start_all")
    public void enrichAll(@RequestParam Integer batchSize) {


        while (true) {

            long startTime = System.currentTimeMillis();

            Slice<PrivatePersonStageRow> batch = privatePersonStageRowRepository.findBy(PageRequest.of(0, batchSize));
            if (!batch.hasContent()) {
                return;
            }
            List<PrivatePersonStageRowArchive> rowsToArchive = new ArrayList<>();
            for (PrivatePersonStageRow row : batch.getContent()) {
                privatePersonStageService.enrichStageRow(row);
                rowsToArchive.add(new PrivatePersonStageRowArchive(row));
            }
            privatePersonStageRowArchiveRepository.saveAll(rowsToArchive);
            privatePersonStageRowRepository.deleteAll(batch.getContent());


            long endTime = System.currentTimeMillis();
            long elapsedTimeMillis = endTime - startTime;

            // Convert milliseconds to minutes, seconds, and milliseconds
            long minutes = (elapsedTimeMillis / 1000) / 60;
            long seconds = (elapsedTimeMillis / 1000) % 60;
            long milliseconds = elapsedTimeMillis % 1000;
            System.out.printf("batch size ( " + (batchSize) + " ): " + " :   %02d:%02d:%03d\n", minutes, seconds, milliseconds);
        }
    }
}

