package com.project.psdprotectiveequipmentservice.entity;

import lombok.*;

import jakarta.persistence.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "protective_equipment")
public class ProtectiveEquipment {
    @Id
    @Column(name = "protective_equipment_id")
    private short protectiveEquipmentId;
    @Column(name = "circuit_breaker_type")
    private String circuitBreakerType;
    @Column(name = "thermal_release_rated_current")
    private float thermalReleaseRatedCurrent;
    @Column(name = "electromagnetic_release_rated_current")
    private float electromagneticReleaseRatedCurrent;
    @Column(name = "circuit_breaker_rated_current")
    private float circuitBreakerRatedCurrent;

    @JoinColumn(name = "low_volt_cables_id_fk")
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private LowVoltCables low_volt_cables_id_fk;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProtectiveEquipment that = (ProtectiveEquipment) o;
        return protectiveEquipmentId == that.protectiveEquipmentId && Float.compare(thermalReleaseRatedCurrent, that.thermalReleaseRatedCurrent) == 0 && Float.compare(electromagneticReleaseRatedCurrent, that.electromagneticReleaseRatedCurrent) == 0 && Float.compare(circuitBreakerRatedCurrent, that.circuitBreakerRatedCurrent) == 0 && Objects.equals(circuitBreakerType, that.circuitBreakerType) && Objects.equals(low_volt_cables_id_fk, that.low_volt_cables_id_fk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(protectiveEquipmentId, circuitBreakerType, thermalReleaseRatedCurrent, electromagneticReleaseRatedCurrent, circuitBreakerRatedCurrent, low_volt_cables_id_fk);
    }
}
