package com.akundu.traveljournal.repository;

import com.akundu.traveljournal.model.RecordsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RecordsRepository extends JpaRepository<RecordsModel, Long> {
    List<RecordsModel> findByRating(Integer rating);
    List<RecordsModel> findByTripDate(LocalDate tripDate);
    List<RecordsModel> findByRatingAndTripDate(Integer rating, LocalDate tripDate);
}