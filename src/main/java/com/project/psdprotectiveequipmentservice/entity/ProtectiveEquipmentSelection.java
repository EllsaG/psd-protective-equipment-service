package com.project.psdprotectiveequipmentservice.entity;

import lombok.*;

import jakarta.persistence.*;

import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "protective_equipment_selection")
public class ProtectiveEquipmentSelection {

    @Id
    @Column(name = "protective_equipment_selection_id")
    private short protectiveEquipmentSelectionId;
    @Column(name = "equipment_rated_current")
    private float equipmentRatedCurrent;
    @Column(name = "equipment_starting_current")
    private float equipmentStartingCurrent;
    @Column(name = "calculated_current_of_thermal_release")
    private float calculatedCurrentOfThermalRelease;
    @Column(name = "calculated_current_of_electromagnetic_release")
    private float calculatedCurrentOfElectromagneticRelease;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProtectiveEquipmentSelection that = (ProtectiveEquipmentSelection) o;
        return protectiveEquipmentSelectionId == that.protectiveEquipmentSelectionId && Float.compare(equipmentRatedCurrent, that.equipmentRatedCurrent) == 0 && Float.compare(equipmentStartingCurrent, that.equipmentStartingCurrent) == 0 && Float.compare(calculatedCurrentOfThermalRelease, that.calculatedCurrentOfThermalRelease) == 0 && Float.compare(calculatedCurrentOfElectromagneticRelease, that.calculatedCurrentOfElectromagneticRelease) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(protectiveEquipmentSelectionId, equipmentRatedCurrent, equipmentStartingCurrent, calculatedCurrentOfThermalRelease, calculatedCurrentOfElectromagneticRelease);
    }
}
