package be.pxl.dice.controller;

import be.pxl.dice.api.CreateDiceSetRequest;
import be.pxl.dice.service.DiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class DiceController {
    private final DiceService diceService;

    @Autowired
    public DiceController(DiceService diceService) {
        this.diceService = diceService;
    }

    @PostMapping("/diceset")
    public ResponseEntity<?> createDice(@RequestBody CreateDiceSetRequest createDiceSetRequest) {
        diceService.createDiceSet(createDiceSetRequest.getNumberOfDice(), createDiceSetRequest.getMaxNumber());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/diceset")
    public ResponseEntity<?> getDiceSet() {
        return ResponseEntity.ok(diceService.getCurrentValues());
    }

    @PostMapping("/diceset/roll")
    public ResponseEntity<?> rollAll() {
        return ResponseEntity.ok(diceService.rollAll());
    }

    @PostMapping("/diceset/roll/{index}")
    public ResponseEntity<?> rollOne(@PathVariable int index) {
        return ResponseEntity.ok(diceService.rollOne(index));
    }

    @GetMapping("/highest-sum")
    public ResponseEntity<?> getHighestSum() {
        return ResponseEntity.ok(diceService.getHighestSum());
    }
}
