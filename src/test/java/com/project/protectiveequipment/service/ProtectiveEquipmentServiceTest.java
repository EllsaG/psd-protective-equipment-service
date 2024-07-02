package com.project.protectiveequipment.service;

import com.project.psdprotectiveequipmentservice.calculation.ProtectiveEquipmentCalculation;
import com.project.psdprotectiveequipmentservice.entity.ProtectiveEquipmentSelection;
import com.project.psdprotectiveequipmentservice.repository.ProtectiveEquipmentRepository;
import com.project.psdprotectiveequipmentservice.service.ProtectiveEquipmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;




@ExtendWith(MockitoExtension.class)
public class ProtectiveEquipmentServiceTest {
    @Mock
    ProtectiveEquipmentRepository protectiveEquipmentRepository;
    @InjectMocks
    ProtectiveEquipmentService protectiveEquipmentService;


    @Test
    public void protectiveEquipmentServiceTest_isAvailable() {
        Mockito.lenient().when(protectiveEquipmentService.isAvailable((short) 4))
                .thenReturn(true);
    }

    @Test
    public void protectiveEquipmentServiceTest_createNewProtectiveEquipmentSelectionInformation() {

        ProtectiveEquipmentCalculation protectiveEquipmentCalculation = new ProtectiveEquipmentCalculation();
        ProtectiveEquipmentSelection newProtectiveEquipmentSelectionInformation = protectiveEquipmentCalculation
                .createNewProtectiveEquipmentSelectionInformation((short) 1, 17.5F, 0.5F);

        Assertions.assertEquals(305.6F, newProtectiveEquipmentSelectionInformation.getEquipmentStartingCurrent());
        Assertions.assertEquals(61.12F, newProtectiveEquipmentSelectionInformation.getEquipmentRatedCurrent());
        Assertions.assertEquals(70.29F, newProtectiveEquipmentSelectionInformation.getCalculatedCurrentOfThermalRelease());
        Assertions.assertEquals(382F, newProtectiveEquipmentSelectionInformation.getCalculatedCurrentOfElectromagneticRelease());
    }

}



