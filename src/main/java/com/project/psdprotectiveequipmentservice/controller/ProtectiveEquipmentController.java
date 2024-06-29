package com.project.psdprotectiveequipmentservice.controller;

import com.project.psdprotectiveequipmentservice.controller.dto.ProtectiveEquipmentRequestDTO;
import com.project.psdprotectiveequipmentservice.controller.dto.ProtectiveEquipmentResponseDTO;
import com.project.psdprotectiveequipmentservice.controller.dto.ProtectiveEquipmentSelectionInformationRequestDTO;
import com.project.psdprotectiveequipmentservice.service.ProtectiveEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProtectiveEquipmentController {

    private final ProtectiveEquipmentService protectiveEquipmentService;

    @Autowired
    public ProtectiveEquipmentController(ProtectiveEquipmentService protectiveEquipmentService) {
        this.protectiveEquipmentService = protectiveEquipmentService;
    }

    @GetMapping("/protectiveEquipment/getAllInformation")
    public ProtectiveEquipmentResponseDTO getAllInformation() {
        ProtectiveEquipmentResponseDTO protectiveEquipmentResponseDTO = new ProtectiveEquipmentResponseDTO();
        protectiveEquipmentResponseDTO.setProtectiveEquipmentList(protectiveEquipmentService.getAllProtectiveEquipment());
        protectiveEquipmentResponseDTO.setProtectiveEquipmentSelectionList(protectiveEquipmentService.getAllProtectiveEquipmentSelectionInformation());

        return protectiveEquipmentResponseDTO;
    }

    @PutMapping("/create/protectiveEquipment")
    public ProtectiveEquipmentResponseDTO createProtectiveEquipment(@RequestBody ProtectiveEquipmentRequestDTO protectiveEquipmentRequestDTO) {
        return protectiveEquipmentService.saveProtectiveEquipment(protectiveEquipmentRequestDTO.getProtectiveEquipmentId(),
                protectiveEquipmentRequestDTO.getThermalReleaseRatedCurrent(),
                protectiveEquipmentRequestDTO.getElectromagneticReleaseRatedCurrent(),
                protectiveEquipmentRequestDTO.getCircuitBreakerRatedCurrent(),
                protectiveEquipmentRequestDTO.getCircuitBreakerType(),protectiveEquipmentRequestDTO.getCableType());
    }

    @PutMapping("/create/selectionInformation")
    public void createProtectiveEquipmentSelectionInformation(@RequestBody ProtectiveEquipmentSelectionInformationRequestDTO
                                                                        protectiveEquipmentSelectionInformationRequestDTO){
        protectiveEquipmentService.saveProtectiveEquipmentSelectionInformation(
                protectiveEquipmentSelectionInformationRequestDTO.getStartInformationId(),
                protectiveEquipmentSelectionInformationRequestDTO.getActivePower(),
                protectiveEquipmentSelectionInformationRequestDTO.getCosf());
    }

    @PutMapping("/update/protectiveEquipment")
    public ProtectiveEquipmentResponseDTO updateProtectiveEquipment(@RequestBody ProtectiveEquipmentRequestDTO protectiveEquipmentRequestDTO) {
        return protectiveEquipmentService.updateProtectiveEquipment(protectiveEquipmentRequestDTO.getProtectiveEquipmentId(),
                protectiveEquipmentRequestDTO.getThermalReleaseRatedCurrent(),
                protectiveEquipmentRequestDTO.getElectromagneticReleaseRatedCurrent(),
                protectiveEquipmentRequestDTO.getCircuitBreakerRatedCurrent(),
                protectiveEquipmentRequestDTO.getCircuitBreakerType(),protectiveEquipmentRequestDTO.getCableType());
    }

    @DeleteMapping("/delete/selectionInformation/{protectiveEquipmentSelectionId}")
    public void deleteProtectiveEquipmentSelectionInformationById(@PathVariable short protectiveEquipmentSelectionId){
        protectiveEquipmentService.deleteProtectiveEquipmentInformationById(protectiveEquipmentSelectionId);

    }

    @DeleteMapping("/delete/protectiveEquipment/{protectiveEquipmentSelectionId}")
    public ProtectiveEquipmentResponseDTO deleteProtectiveEquipment(@PathVariable short protectiveEquipmentSelectionId){
        return protectiveEquipmentService.deleteProtectiveEquipmentById(protectiveEquipmentSelectionId);
    }

    @GetMapping("/check/{protectiveEquipmentSelectionId}")
    public Boolean checkAvailability(@PathVariable short protectiveEquipmentSelectionId){
        return protectiveEquipmentService.isAvailable(protectiveEquipmentSelectionId);
    }


}
