package com.project.psdprotectiveequipmentservice.repository;

import com.project.psdprotectiveequipmentservice.entity.ProtectiveEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtectiveEquipmentRepository extends JpaRepository<ProtectiveEquipment,Short> {
}
