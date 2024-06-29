package com.project.psdprotectiveequipmentservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "low_volt_cables")
public class LowVoltCables {

    @Id
    @Column(name = "low_volt_cables_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private short lowVoltCablesId;
    @Column(name = "cable_type")
    @Setter
    private String cableType;

    public LowVoltCables(String cableType) {
        this.cableType = cableType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LowVoltCables that = (LowVoltCables) o;
        return lowVoltCablesId == that.lowVoltCablesId && Objects.equals(cableType, that.cableType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lowVoltCablesId, cableType);
    }
}
