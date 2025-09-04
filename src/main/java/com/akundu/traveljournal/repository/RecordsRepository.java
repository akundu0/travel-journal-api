package com.akundu.traveljournal.repository;

import com.akundu.traveljournal.model.RecordsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

public interface RecordsRepository extends JpaRepository<RecordsModel, Long>, JpaSpecificationExecutor<RecordsModel> {

}