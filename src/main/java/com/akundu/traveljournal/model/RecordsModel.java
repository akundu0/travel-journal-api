package com.akundu.traveljournal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity

public class RecordsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Destination is required")
    private String destination;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer rating;

    private String notes;

    private LocalDate tripDate;

    private LocalDateTime createdAt;

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
