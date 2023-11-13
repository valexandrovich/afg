package ua.com.valexa.enricher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.valexa.db.model.stage.PrivatePersonStageRow;
import ua.com.valexa.enricher.service.PrivatePersonStageService;

import java.util.UUID;

@RestController
@RequestMapping("/pp")
public class PrivatePersonController {

    @Autowired
    PrivatePersonStageService privatePersonStageService;
    @PostMapping("/stage/{id}")
    public void enrichPrivatePersonStage(@PathVariable UUID id){
        privatePersonStageService.enrichStageRow(id);
    }

    @PostMapping("/stage")
    public void enrichPrivatePersonStageRow(
            @RequestBody PrivatePersonStageRow row
            ) {
        privatePersonStageService.enrichStageRow(row);
    }
}
