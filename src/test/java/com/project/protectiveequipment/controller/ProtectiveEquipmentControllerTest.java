package com.project.protectiveequipment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.protectiveequipment.config.SpringH2DatabaseConfig;
import com.project.psdprotectiveequipmentservice.PsdProtectiveEquipmentServiceApplication;
import com.project.psdprotectiveequipmentservice.controller.dto.ProtectiveEquipmentRequestDTO;
import com.project.psdprotectiveequipmentservice.controller.dto.ProtectiveEquipmentResponseDTO;
import com.project.psdprotectiveequipmentservice.controller.dto.ProtectiveEquipmentSelectionInformationRequestDTO;
import com.project.psdprotectiveequipmentservice.entity.ProtectiveEquipment;
import com.project.psdprotectiveequipmentservice.entity.ProtectiveEquipmentSelection;
import com.project.psdprotectiveequipmentservice.repository.ProtectiveEquipmentRepository;
import com.project.psdprotectiveequipmentservice.repository.ProtectiveEquipmentSelectionRepository;
import org.junit.jupiter.api.Assertions;




import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {PsdProtectiveEquipmentServiceApplication.class, SpringH2DatabaseConfig.class})
public class ProtectiveEquipmentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ProtectiveEquipmentRepository protectiveEquipmentRepository;

    @Autowired
    private ProtectiveEquipmentSelectionRepository protectiveEquipmentSelectionRepository;


    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    @Sql(scripts = {"/sql/clearProtectiveEquipmentSelectionDB.sql", "/sql/clearProtectiveEquipmentDB.sql",
            "/sql/addProtectiveEquipmentSelectionInformation.sql", "/sql/addProtectiveEquipmentInformation.sql"})
    public void getAllInformation() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/getAllInformation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk())
                .andReturn();

        List<ProtectiveEquipment> protectiveEquipmentRepositoryAll = protectiveEquipmentRepository.findAll();
        ProtectiveEquipment protectiveEquipmentRepository1 = protectiveEquipmentRepositoryAll.get(0);
        ProtectiveEquipment protectiveEquipmentRepository2 = protectiveEquipmentRepositoryAll.get(1);

        List<ProtectiveEquipmentSelection> protectiveEquipmentSelectionRepositoryAll = protectiveEquipmentSelectionRepository.findAll();
        ProtectiveEquipmentSelection protectiveEquipmentSelectionRepository1 = protectiveEquipmentSelectionRepositoryAll.get(0);
        ProtectiveEquipmentSelection protectiveEquipmentSelectionRepository2 = protectiveEquipmentSelectionRepositoryAll.get(1);

        String body = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        ProtectiveEquipmentResponseDTO protectiveEquipmentResponseDTO = objectMapper
                .readValue(body, ProtectiveEquipmentResponseDTO.class);
        ProtectiveEquipment protectiveEquipmentResponse1 = protectiveEquipmentResponseDTO.getProtectiveEquipmentList().get(0);
        ProtectiveEquipment protectiveEquipmentResponse2 = protectiveEquipmentResponseDTO.getProtectiveEquipmentList().get(1);

        ProtectiveEquipmentSelection protectiveEquipmentSelectionResponse1 = protectiveEquipmentResponseDTO.getProtectiveEquipmentSelectionList().get(0);
        ProtectiveEquipmentSelection protectiveEquipmentSelectionResponse2 = protectiveEquipmentResponseDTO.getProtectiveEquipmentSelectionList().get(1);


        Assertions.assertEquals(protectiveEquipmentRepository1.getElectromagneticReleaseRatedCurrent(),
                protectiveEquipmentResponse1.getElectromagneticReleaseRatedCurrent());
        Assertions.assertEquals(protectiveEquipmentRepository1.getThermalReleaseRatedCurrent(),
                protectiveEquipmentResponse1.getThermalReleaseRatedCurrent());
        Assertions.assertEquals(protectiveEquipmentRepository1.getCircuitBreakerRatedCurrent(),
                protectiveEquipmentResponse1.getCircuitBreakerRatedCurrent());
        Assertions.assertEquals(protectiveEquipmentRepository1.getCircuitBreakerType(),
                protectiveEquipmentResponse1.getCircuitBreakerType());
        Assertions.assertEquals(protectiveEquipmentRepository1.getLow_volt_cables_id_fk().getCableType(),
                protectiveEquipmentResponse1.getLow_volt_cables_id_fk().getCableType());

        Assertions.assertEquals(protectiveEquipmentRepository2.getElectromagneticReleaseRatedCurrent(),
                protectiveEquipmentResponse2.getElectromagneticReleaseRatedCurrent());
        Assertions.assertEquals(protectiveEquipmentRepository2.getThermalReleaseRatedCurrent(),
                protectiveEquipmentResponse2.getThermalReleaseRatedCurrent());
        Assertions.assertEquals(protectiveEquipmentRepository2.getCircuitBreakerRatedCurrent(),
                protectiveEquipmentResponse2.getCircuitBreakerRatedCurrent());
        Assertions.assertEquals(protectiveEquipmentRepository2.getCircuitBreakerType(),
                protectiveEquipmentResponse2.getCircuitBreakerType());
        Assertions.assertEquals(protectiveEquipmentRepository2.getLow_volt_cables_id_fk().getCableType(),
                protectiveEquipmentResponse2.getLow_volt_cables_id_fk().getCableType());

        Assertions.assertEquals(protectiveEquipmentSelectionRepository1.getEquipmentStartingCurrent(),
                protectiveEquipmentSelectionResponse1.getEquipmentStartingCurrent());
        Assertions.assertEquals(protectiveEquipmentSelectionRepository1.getEquipmentRatedCurrent(),
                protectiveEquipmentSelectionResponse1.getEquipmentRatedCurrent());
        Assertions.assertEquals(protectiveEquipmentSelectionRepository1.getCalculatedCurrentOfThermalRelease(),
                protectiveEquipmentSelectionResponse1.getCalculatedCurrentOfThermalRelease());
        Assertions.assertEquals(protectiveEquipmentSelectionRepository1.getCalculatedCurrentOfElectromagneticRelease(),
                protectiveEquipmentSelectionResponse1.getCalculatedCurrentOfElectromagneticRelease());

        Assertions.assertEquals(protectiveEquipmentSelectionRepository2.getEquipmentStartingCurrent(),
                protectiveEquipmentSelectionResponse2.getEquipmentStartingCurrent());
        Assertions.assertEquals(protectiveEquipmentSelectionRepository2.getEquipmentRatedCurrent(),
                protectiveEquipmentSelectionResponse2.getEquipmentRatedCurrent());
        Assertions.assertEquals(protectiveEquipmentSelectionRepository2.getCalculatedCurrentOfThermalRelease(),
                protectiveEquipmentSelectionResponse2.getCalculatedCurrentOfThermalRelease());
        Assertions.assertEquals(protectiveEquipmentSelectionRepository2.getCalculatedCurrentOfElectromagneticRelease(),
                protectiveEquipmentSelectionResponse2.getCalculatedCurrentOfElectromagneticRelease());

    }

    @Test
    @Sql(scripts = {"/sql/clearProtectiveEquipmentSelectionDB.sql", "/sql/addProtectiveEquipmentSelectionInformation.sql"})
    public void createProtectiveEquipment() throws Exception {

        String REQUEST = createProtectiveEquipmentRequestAsString();

        MvcResult mvcResult = mockMvc.perform(put("/create/protectiveEquipment")
                        .content(REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk())
                .andReturn();

        ProtectiveEquipment byId = protectiveEquipmentRepository.findById((short) 1).orElseThrow(() -> new NoSuchElementException("No value present"));
        String body = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        ProtectiveEquipmentResponseDTO protectiveEquipmentResponseDTO = objectMapper
                .readValue(body, ProtectiveEquipmentResponseDTO.class);
        ProtectiveEquipment protectiveEquipment = protectiveEquipmentResponseDTO.getProtectiveEquipmentList().get(0);

        Assertions.assertEquals(byId.getElectromagneticReleaseRatedCurrent(), protectiveEquipment.getElectromagneticReleaseRatedCurrent());
        Assertions.assertEquals(byId.getThermalReleaseRatedCurrent(), protectiveEquipment.getThermalReleaseRatedCurrent());
        Assertions.assertEquals(byId.getCircuitBreakerRatedCurrent(), protectiveEquipment.getCircuitBreakerRatedCurrent());
        Assertions.assertEquals(byId.getCircuitBreakerType(), protectiveEquipment.getCircuitBreakerType());
        Assertions.assertEquals(byId.getLow_volt_cables_id_fk().getCableType(),
                protectiveEquipment.getLow_volt_cables_id_fk().getCableType());

    }


    @Test
    @Sql(scripts = {"/sql/clearProtectiveEquipmentSelectionDB.sql"})
    public void createProtectiveEquipmentSelectionInformation() throws Exception {

        String REQUEST = createProtectiveEquipmentSelectionInformationRequestAsString();

        mockMvc.perform(put("/create/selectionInformation")
                        .content(REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk())
                .andReturn();

        ProtectiveEquipmentSelection byId = protectiveEquipmentSelectionRepository.findById((short) 1).orElseThrow(() -> new NoSuchElementException("No value present"));

        Assertions.assertEquals(305.6F, byId.getEquipmentStartingCurrent());
        Assertions.assertEquals(61.12F, byId.getEquipmentRatedCurrent());
        Assertions.assertEquals(70.29F, byId.getCalculatedCurrentOfThermalRelease());
        Assertions.assertEquals(382F, byId.getCalculatedCurrentOfElectromagneticRelease());

    }

    @Test
    @Sql(scripts = {"/sql/clearProtectiveEquipmentSelectionDB.sql", "/sql/clearProtectiveEquipmentDB.sql",
            "/sql/addProtectiveEquipmentSelectionInformation.sql", "/sql/addProtectiveEquipmentInformationOne.sql"})
    public void updateProtectiveEquipment() throws Exception {

        String REQUEST = createProtectiveEquipmentUpdateRequestAsString();

        MvcResult mvcResult = mockMvc.perform(put("/update/protectiveEquipment")
                        .content(REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk())
                .andReturn();

        ProtectiveEquipment byId = protectiveEquipmentRepository.findById((short) 1).orElseThrow(() -> new NoSuchElementException("No value present"));
        String body = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        ProtectiveEquipmentResponseDTO protectiveEquipmentResponseDTO = objectMapper
                .readValue(body, ProtectiveEquipmentResponseDTO.class);
        ProtectiveEquipment protectiveEquipment = protectiveEquipmentResponseDTO.getProtectiveEquipmentList().get(0);

        Assertions.assertEquals(byId.getElectromagneticReleaseRatedCurrent(), protectiveEquipment.getElectromagneticReleaseRatedCurrent());
        Assertions.assertEquals(byId.getThermalReleaseRatedCurrent(), protectiveEquipment.getThermalReleaseRatedCurrent());
        Assertions.assertEquals(byId.getCircuitBreakerRatedCurrent(), protectiveEquipment.getCircuitBreakerRatedCurrent());
        Assertions.assertEquals(byId.getCircuitBreakerType(), protectiveEquipment.getCircuitBreakerType());
        Assertions.assertEquals(byId.getLow_volt_cables_id_fk().getCableType(),
                protectiveEquipment.getLow_volt_cables_id_fk().getCableType());

    }

    @Test
    @Sql(scripts = {"/sql/clearProtectiveEquipmentSelectionDB.sql", "/sql/addProtectiveEquipmentSelectionInformation.sql"})
    public void deleteProtectiveEquipmentSelectionInformationById() throws Exception {

        mockMvc.perform(delete("/delete/selectionInformation/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk())
                .andReturn();

        List<ProtectiveEquipmentSelection> protectiveEquipmentSelectionRepositoryAll = protectiveEquipmentSelectionRepository.findAll();
        ProtectiveEquipmentSelection protectiveEquipmentSelection = protectiveEquipmentSelectionRepositoryAll.get(0);

        Assertions.assertEquals(1, protectiveEquipmentSelection.getProtectiveEquipmentSelectionId());
        Assertions.assertEquals(382F, protectiveEquipmentSelection.getEquipmentStartingCurrent());
        Assertions.assertEquals(70.29F, protectiveEquipmentSelection.getEquipmentRatedCurrent());
        Assertions.assertEquals(61.12F, protectiveEquipmentSelection.getCalculatedCurrentOfThermalRelease());
        Assertions.assertEquals(305.6F, protectiveEquipmentSelection.getCalculatedCurrentOfElectromagneticRelease());

    }

    @Test
    @Sql(scripts = {"/sql/clearProtectiveEquipmentDB.sql", "/sql/addProtectiveEquipmentInformation.sql"})
    public void deleteProtectiveEquipment() throws Exception {

        MvcResult mvcResult = mockMvc.perform(delete("/delete/protectiveEquipment/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk())
                .andReturn();

        List<ProtectiveEquipment> protectiveEquipmentRepositoryAll = protectiveEquipmentRepository.findAll();
        ProtectiveEquipment protectiveEquipmentRepository = protectiveEquipmentRepositoryAll.get(0);
        String body = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        ProtectiveEquipmentResponseDTO protectiveEquipmentResponseDTO = objectMapper
                .readValue(body, ProtectiveEquipmentResponseDTO.class);
        ProtectiveEquipment protectiveEquipment = protectiveEquipmentResponseDTO.getProtectiveEquipmentList().get(0);

        Assertions.assertEquals(protectiveEquipmentRepository.getProtectiveEquipmentId(), protectiveEquipment.getProtectiveEquipmentId());
        Assertions.assertEquals(protectiveEquipmentRepository.getElectromagneticReleaseRatedCurrent(), protectiveEquipment.getElectromagneticReleaseRatedCurrent());
        Assertions.assertEquals(protectiveEquipmentRepository.getThermalReleaseRatedCurrent(), protectiveEquipment.getThermalReleaseRatedCurrent());
        Assertions.assertEquals(protectiveEquipmentRepository.getCircuitBreakerRatedCurrent(), protectiveEquipment.getCircuitBreakerRatedCurrent());
        Assertions.assertEquals(protectiveEquipmentRepository.getCircuitBreakerType(), protectiveEquipment.getCircuitBreakerType());
        Assertions.assertEquals(protectiveEquipmentRepository.getLow_volt_cables_id_fk().getCableType(),
                protectiveEquipment.getLow_volt_cables_id_fk().getCableType());

    }

    @Test
    @Sql(scripts = {"/sql/clearProtectiveEquipmentDB.sql", "/sql/addProtectiveEquipmentInformation.sql"})
    public void checkAvailability() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/check/1"))
                .andExpect(status()
                        .isOk())
                .andReturn();

        boolean fromRepository = protectiveEquipmentRepository.existsById((short) 1);
        String body = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        boolean fromResponse = objectMapper.readValue(body, Boolean.class);

        Assertions.assertEquals(fromRepository, fromResponse);
    }

    private String createProtectiveEquipmentSelectionInformationRequestAsString() throws JsonProcessingException {
        ProtectiveEquipmentSelectionInformationRequestDTO protectiveEquipmentSelectionInformationRequestDTO =
                new ProtectiveEquipmentSelectionInformationRequestDTO((short) 1, 17.5F, 0.5F);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(protectiveEquipmentSelectionInformationRequestDTO);

    }

    private String createProtectiveEquipmentRequestAsString() throws JsonProcessingException {
        ProtectiveEquipmentRequestDTO protectiveEquipmentRequestDTO =
                new ProtectiveEquipmentRequestDTO(
                        (short) 1, "ВА 47-100", 80.0F,
                        800.0F, 80.0F, "ВВГ 4х16");

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(protectiveEquipmentRequestDTO);

    }

    private String createProtectiveEquipmentUpdateRequestAsString() throws JsonProcessingException {
        ProtectiveEquipmentRequestDTO protectiveEquipmentRequestDTO =
                new ProtectiveEquipmentRequestDTO(
                        (short) 1, "ВА 47-50", 20.0F,
                        200.0F, 20.0F, "ВВГ 3х6");

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(protectiveEquipmentRequestDTO);

    }


}
