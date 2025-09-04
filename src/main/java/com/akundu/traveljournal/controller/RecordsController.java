package com.akundu.traveljournal.controller;

import com.akundu.traveljournal.model.RecordsModel;
import com.akundu.traveljournal.repository.RecordsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
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
    public RecordsModel createJournal(@Valid @RequestBody RecordsModel journal) {
        if (journal.getTripDate() == null) {
            journal.setTripDate(LocalDate.now());
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

    @PutMapping("/{id}")
    public RecordsModel updateJournal(@PathVariable Long id, @Valid @RequestBody RecordsModel updatedJournal) {
        if (updatedJournal.getTripDate() == null) {
            updatedJournal.setTripDate(LocalDate.now());
        }
        return repository.findById(id)
                .map(existingJournal -> {
                    existingJournal.setTitle(updatedJournal.getTitle());
                    existingJournal.setDestination(updatedJournal.getDestination());
                    existingJournal.setRating(updatedJournal.getRating());
                    existingJournal.setNotes(updatedJournal.getNotes());
                    existingJournal.setTripDate(updatedJournal.getTripDate());

                    return repository.save(existingJournal);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Journal entry not found with id " + id
                ));
    }
}
