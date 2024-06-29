package com.project.psdprotectiveequipmentservice.controller.dto;


import com.project.psdprotectiveequipmentservice.entity.ProtectiveEquipment;
import com.project.psdprotectiveequipmentservice.entity.ProtectiveEquipmentSelection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProtectiveEquipmentResponseDTO {
    List<ProtectiveEquipment> protectiveEquipmentList;
    List<ProtectiveEquipmentSelection> protectiveEquipmentSelectionList;
}
