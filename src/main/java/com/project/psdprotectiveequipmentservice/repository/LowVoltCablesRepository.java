package com.project.psdprotectiveequipmentservice.repository;

import com.project.psdprotectiveequipmentservice.entity.LowVoltCables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LowVoltCablesRepository extends JpaRepository<LowVoltCables,Short> {
}
