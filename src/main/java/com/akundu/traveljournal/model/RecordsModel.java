package com.akundu.traveljournal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
//@Table(name = "records_model")
public class RecordsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String destination;
    private Integer rating;
    private LocalDate tripDate;
    private LocalDateTime createdAt;
    private String notes;

    public RecordsModel() {}
    public RecordsModel(String title, String destination, Integer rating, String notes, LocalDate tripDate) {
        this.title = title;
        this.destination = destination;
        this.rating = rating;
        this.notes = notes;
        this.tripDate = tripDate;
        this.createdAt = LocalDateTime.now();
    }
}
