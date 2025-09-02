package com.akundu.traveljournal.controller;

import com.akundu.traveljournal.model.RecordsModel;
import com.akundu.traveljournal.repository.RecordsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/journals")
public class RecordsController {
    private final RecordsRepository repository;

    public RecordsController(RecordsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<RecordsModel> getAllJournals() {
        return repository.findAll();
    }

    @PostMapping
    public RecordsModel createJournal(@RequestBody RecordsModel journal) {
        if (journal.getTitle() == null || journal.getTitle().trim().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Title is required"
            );
        }

        if (journal.getDestination() == null || journal.getDestination().trim().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Destination is required"
            );
        }

        if (journal.getTripDate() == null) {
            journal.setTripDate(LocalDate.now()); // default to today if not provided
        }

        if (journal.getRating() == null || journal.getRating() < 1 || journal.getRating() > 5) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Rating must be between 1 and 5"
            );
        }
        journal.setCreatedAt(LocalDateTime.now());
        return repository.save(journal);
    }

    @GetMapping("/{id}")
    public RecordsModel getJournal(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @GetMapping("/filter")
    public List<RecordsModel> filterJournals(
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) String date) {

        LocalDate tripDate = (date != null) ? LocalDate.parse(date) : null;

        if (rating != null && tripDate != null) {
            return repository.findByRatingAndTripDate(rating, tripDate);
        } else if (rating != null) {
            return repository.findByRating(rating);
        } else if (tripDate != null) {
            return repository.findByTripDate(tripDate);
        } else {
            return repository.findAll();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteJournal(@PathVariable Long id) {
        repository.deleteById(id);
    }


}
