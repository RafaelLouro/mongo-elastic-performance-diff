package test.mongoWorker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.mongoWorker.service.ImportToMongoCommand;
import test.mongoWorker.service.PopulatePostgresCommand;

@RestController
@RequestMapping("/import")
public class ImportProcessController {

    private ImportToMongoCommand importToMongoCommand;

    private PopulatePostgresCommand populatePostgresCommand;

    public ImportProcessController(ImportToMongoCommand importToMongoCommand, PopulatePostgresCommand populatePostgresCommand) {
        this.importToMongoCommand = importToMongoCommand;
        this.populatePostgresCommand = populatePostgresCommand;
    }

    @GetMapping("/start")
    public ResponseEntity startImport() {
        importToMongoCommand.execute();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/populate")
    public ResponseEntity populate() {
        populatePostgresCommand.execute();
        return ResponseEntity.ok().build();
    }
}
