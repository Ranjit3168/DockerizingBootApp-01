package com.bpl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bpl.entity.Touriest;

import jakarta.transaction.Transactional;

public interface ITouriestRepository extends JpaRepository<Touriest, Integer> {
   @Query("from Touriest where budget>=:start and budget<=:end")
	public List<Touriest> showTouriestByBudget(double start,double end);
   
   @Query("delete from Touriest where packageType=:type")
   @Modifying
   @Transactional
   public int deleteTouriestByPackageType(String type);
}
