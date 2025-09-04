package com.akundu.traveljournal.specification;

import com.akundu.traveljournal.model.RecordsModel;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class RecordsSpecification {
    public static Specification<RecordsModel> hasRating(Integer rating) {
        return (root, query, cb) ->
                rating == null ? null : cb.equal(root.get("rating"), rating);
    }

    public static Specification<RecordsModel> hasTripDate(LocalDate tripDate) {
        return (root, query, cb) ->
                tripDate == null ? null : cb.equal(root.get("tripDate"), tripDate);
    }

    public static Specification<RecordsModel> hasDestination(String destination) {
        return (root, query, cb) ->
                (destination == null || destination.isEmpty())
                        ? null
                        : cb.like(cb.lower(root.get("destination")), "%" + destination.toLowerCase() + "%");
    }
}
