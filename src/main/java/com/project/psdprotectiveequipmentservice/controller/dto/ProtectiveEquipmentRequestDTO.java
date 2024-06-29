package com.project.psdprotectiveequipmentservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProtectiveEquipmentRequestDTO {

    @JsonProperty("protectiveEquipmentId")
    private short protectiveEquipmentId;
    @JsonProperty("circuitBreakerType")
    private String circuitBreakerType;
    @JsonProperty("thermalReleaseRatedCurrent")
    private float thermalReleaseRatedCurrent;
    @JsonProperty("electromagneticReleaseRatedCurrent")
    private float electromagneticReleaseRatedCurrent;
    @JsonProperty("circuitBreakerRatedCurrent")
    private float circuitBreakerRatedCurrent;
    @JsonProperty("cableType")
    private String cableType;
}
