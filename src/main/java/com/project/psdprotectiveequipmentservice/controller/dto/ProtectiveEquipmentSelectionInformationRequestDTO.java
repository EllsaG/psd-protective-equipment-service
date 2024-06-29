package com.project.psdprotectiveequipmentservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProtectiveEquipmentSelectionInformationRequestDTO {

    @JsonProperty("startInformationId")
    private short startInformationId;
    @JsonProperty("activePower")
    private float activePower;
    @JsonProperty("cosf")
    private float cosf;
}
