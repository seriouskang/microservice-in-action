package com.example.bestbook.presentation;

import com.example.bestbook.application.BestBookService;
import com.example.bestbook.domain.model.BestBook;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/best-books")
@RequiredArgsConstructor
public class BestBookController {
    private final BestBookService bestBookService;

    @GetMapping
    public ResponseEntity<List<BestBook>> bestBooks() {
        List<BestBook> found = bestBookService.findAll();
        return ResponseEntity.ok(found);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BestBook> bestBook(@PathVariable String id) {
        Optional<BestBook> found = bestBookService.findById(id);
        return found.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<BestBook> create(@RequestBody BestBook bestBook) {
        BestBook created = bestBookService.save(bestBook);
        return new ResponseEntity<>(created, CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BestBook> update(@PathVariable String id, @RequestBody BestBook bestBook) {
        BestBook updated = bestBookService.update(id, bestBook);
        return updated != null ?
                ResponseEntity.ok(updated) : new ResponseEntity<>(NOT_FOUND);
    }
}
