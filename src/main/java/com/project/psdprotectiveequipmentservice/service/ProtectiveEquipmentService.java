package com.project.psdprotectiveequipmentservice.service;

import com.project.psdprotectiveequipmentservice.calculation.ProtectiveEquipmentCalculation;
import com.project.psdprotectiveequipmentservice.controller.dto.ProtectiveEquipmentResponseDTO;
import com.project.psdprotectiveequipmentservice.entity.ProtectiveEquipment;
import com.project.psdprotectiveequipmentservice.entity.ProtectiveEquipmentSelection;
import com.project.psdprotectiveequipmentservice.exeptions.InformationNotFoundException;
import com.project.psdprotectiveequipmentservice.repository.ProtectiveEquipmentRepository;
import com.project.psdprotectiveequipmentservice.repository.ProtectiveEquipmentSelectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProtectiveEquipmentService {

    private final ProtectiveEquipmentRepository protectiveEquipmentRepository;
    private final ProtectiveEquipmentSelectionRepository protectiveEquipmentSelectionRepository;

    @Autowired
    public ProtectiveEquipmentService(ProtectiveEquipmentRepository protectiveEquipmentRepository,
                                      ProtectiveEquipmentSelectionRepository protectiveEquipmentSelectionRepository) {
        this.protectiveEquipmentRepository = protectiveEquipmentRepository;
        this.protectiveEquipmentSelectionRepository = protectiveEquipmentSelectionRepository;
    }





    public ProtectiveEquipmentResponseDTO saveProtectiveEquipment(short id, float nominalCurrentOfThermalRelease, float nominalCurrentOfElectromagneticRelease,
                                               float nominalCurrentOfCircuitBreaker, String typeOfCircuitBreaker, String cableType) {

        ProtectiveEquipmentCalculation protectiveEquipmentCalculation = new ProtectiveEquipmentCalculation();
        ProtectiveEquipment newProtectiveEquipment = protectiveEquipmentCalculation.createNewProtectiveEquipment(id, nominalCurrentOfThermalRelease,
                nominalCurrentOfElectromagneticRelease, nominalCurrentOfCircuitBreaker, typeOfCircuitBreaker, cableType
                , protectiveEquipmentSelectionRepository);

        protectiveEquipmentRepository.save(newProtectiveEquipment);

        return new ProtectiveEquipmentResponseDTO(getAllProtectiveEquipment(), getAllProtectiveEquipmentSelectionInformation());
    }

    public void saveProtectiveEquipmentSelectionInformation(short startInformationId, float activePower, float cosf) {

        ProtectiveEquipmentCalculation protectiveEquipmentCalculation = new ProtectiveEquipmentCalculation();
        ProtectiveEquipmentSelection newProtectiveEquipmentSelectionInformation =
                protectiveEquipmentCalculation.createNewProtectiveEquipmentSelectionInformation(startInformationId,activePower, cosf);

        protectiveEquipmentSelectionRepository.save(newProtectiveEquipmentSelectionInformation);
    }

    public ProtectiveEquipmentResponseDTO updateProtectiveEquipment(short id, float nominalCurrentOfThermalRelease, float nominalCurrentOfElectromagneticRelease,
                                                                    float nominalCurrentOfCircuitBreaker, String typeOfCircuitBreaker, String cableType) {
        deleteProtectiveEquipmentById(id);
        return saveProtectiveEquipment(id, nominalCurrentOfThermalRelease, nominalCurrentOfElectromagneticRelease,
                nominalCurrentOfCircuitBreaker, typeOfCircuitBreaker, cableType);
    }


    public ProtectiveEquipmentResponseDTO deleteProtectiveEquipmentById(short protectiveEquipmentId) {

        if (protectiveEquipmentRepository.existsById(protectiveEquipmentId)){
            protectiveEquipmentRepository.deleteById(protectiveEquipmentId);
        }else {
            throw new InformationNotFoundException("Unable to find information about the protected equipment. Check the availability of this equipment.");
        }
        return new ProtectiveEquipmentResponseDTO(getAllProtectiveEquipment(),
                getAllProtectiveEquipmentSelectionInformation());
    }

    public void deleteProtectiveEquipmentInformationById(short protectiveEquipmentId) {
        if (protectiveEquipmentSelectionRepository.existsById(protectiveEquipmentId)){
            protectiveEquipmentSelectionRepository.deleteById(protectiveEquipmentId);
        }
    }

    public List<ProtectiveEquipment> getAllProtectiveEquipment() {
        return protectiveEquipmentRepository.findAll();
    }

    public List<ProtectiveEquipmentSelection> getAllProtectiveEquipmentSelectionInformation() {
        return protectiveEquipmentSelectionRepository.findAll();
    }

    public Boolean isAvailable(short protectiveEquipmentSelectionId) {
        return protectiveEquipmentRepository.existsById(protectiveEquipmentSelectionId);
    }
}
