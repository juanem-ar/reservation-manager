package com.complejolapasionaria.reservation.repository;

import com.complejolapasionaria.reservation.model.RentalUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRentalUnitRepository extends JpaRepository<RentalUnit,Long> {
    boolean existsByName(String name);
}