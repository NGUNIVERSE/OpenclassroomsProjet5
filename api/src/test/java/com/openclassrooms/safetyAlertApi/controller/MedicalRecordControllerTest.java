
package com.openclassrooms.safetyAlertApi.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyAlertApi.model.MedicalRecord;
import com.openclassrooms.safetyAlertApi.service.MedicalRecordService;

@WebMvcTest(controllers = MedicalRecordController.class)
public class MedicalRecordControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MedicalRecordService medicalRecordServiceMock;

	private MedicalRecord mockMedicalRecord() {

		MedicalRecord medicalRecordMock = new MedicalRecord();
		medicalRecordMock.setFirstname("Reginold");
		medicalRecordMock.setLastname("Walker");
		medicalRecordMock.setBirthdate("08/30/1979");
		medicalRecordMock.setMedications(List.of("thradox:700mg"));
		medicalRecordMock.setAllergies(List.of("illisoxian"));
		return medicalRecordMock;
	}

	private MedicalRecord mockMedicalRecord1() {

		MedicalRecord medicalRecordMock1 = new MedicalRecord();
		medicalRecordMock1.setFirstname("Ron");
		medicalRecordMock1.setLastname("Peters");
		medicalRecordMock1.setBirthdate("04/06/1965");
		medicalRecordMock1.setMedications(List.of(""));
		medicalRecordMock1.setAllergies(List.of(""));
		return medicalRecordMock1;
	}

	private long id1 = 0;
	private long id2 = 1;
	private long id3 = 50;

	@Test
	public void testGetMedicalRecord() throws Exception {

		MedicalRecord medicalRecordMock = mockMedicalRecord();
		MedicalRecord medicalRecordMock1 = mockMedicalRecord1();

		Optional<MedicalRecord> medicalRecordOptionalMock = Optional.ofNullable(medicalRecordMock);
		Optional<MedicalRecord> medicalRecordOptionalMock1 = Optional.ofNullable(medicalRecordMock1);

		when(medicalRecordServiceMock.getMedicalRecord(id1)).thenReturn(medicalRecordOptionalMock);
		when(medicalRecordServiceMock.getMedicalRecord(id2)).thenReturn(medicalRecordOptionalMock1);

		mockMvc.perform(get("/medicalRecord/{id}", id1)).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("Reginold")).andExpect(jsonPath("$.lastName").value("Walker"))
				.andExpect(jsonPath("$.birthdate").value("08/30/1979"))
				.andExpect(jsonPath("$.medications").value("thradox:700mg"))
				.andExpect(jsonPath("$.allergies").value("illisoxian"));

		mockMvc.perform(get("/medicalRecord/{id}", id2)).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("Ron")).andExpect(jsonPath("$.lastName").value("Peters"))
				.andExpect(jsonPath("$.birthdate").value("04/06/1965")).andExpect(jsonPath("$.medications").value(""))
				.andExpect(jsonPath("$.allergies").value(""));

		verify(medicalRecordServiceMock).getMedicalRecord(id1);
		verify(medicalRecordServiceMock).getMedicalRecord(id2);

	}

	@Test
	public void testGetMedicalRecords() throws Exception {

		MedicalRecord medicalRecordMock = mockMedicalRecord();
		MedicalRecord medicalRecordMock1 = mockMedicalRecord1();

		List<MedicalRecord> listOfMedicalRecord = new ArrayList<>();
		listOfMedicalRecord.add(medicalRecordMock);
		listOfMedicalRecord.add(medicalRecordMock1);
		Iterable<MedicalRecord> listOfMedicalRecordTest = listOfMedicalRecord;

		when(medicalRecordServiceMock.getMedicalRecords()).thenReturn(listOfMedicalRecordTest);

		mockMvc.perform(get("/medicalRecords")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstName", is("Reginold")))
				.andExpect(jsonPath("$[0].lastName", is("Walker")))
				.andExpect(jsonPath("$[0].birthdate", is("08/30/1979")))
				// .andExpect(jsonPath("$[0].medications").isArray())
				.andExpect(jsonPath("$[0].medications[0]", is("thradox:700mg")))
				.andExpect(jsonPath("$[0].allergies[0]", is("illisoxian")))
				.andExpect(jsonPath("$[1].firstName", is("Ron"))).andExpect(jsonPath("$[1].lastName", is("Peters")))
				.andExpect(jsonPath("$[1].birthdate", is("04/06/1965")))
				.andExpect(jsonPath("$[1].medications[0]", is(""))).andExpect(jsonPath("$[1].allergies[0]", is("")));

		verify(medicalRecordServiceMock).getMedicalRecords();

	}

	@Test
	public void testDeleteMedicalRecord() throws Exception {

		mockMvc.perform(delete("/medicalRecord/{id}", id2)).andExpect(status().isNoContent());
		verify(medicalRecordServiceMock).deleteMedicalRecord(id2);

	}

	@Test
	public void testGetMedicalRecordUnknownId() throws Exception {

		mockMvc.perform(get("/medicalRecord/{id}", id3)).andExpect(status().isNotFound());
		verify(medicalRecordServiceMock).getMedicalRecord(id3);

	}

	@Test
	public void testCreateMedicalRecord() throws Exception {

		MedicalRecord medicalRecordMock = mockMedicalRecord();
		MedicalRecord medicalRecordMock1 = mockMedicalRecord1();

		List<MedicalRecord> listOfMedicalRecord = new ArrayList<>();
		listOfMedicalRecord.add(medicalRecordMock);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(medicalRecordMock1);
		when(medicalRecordServiceMock.saveMedicalRecord(medicalRecordMock1)).thenReturn(medicalRecordMock1);

		mockMvc.perform(post("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName").value("Ron")).andExpect(jsonPath("$.lastName").value("Peters"))
				.andExpect(jsonPath("$.birthdate").value("04/06/1965")).andExpect(jsonPath("$.medications").value(""))
				.andExpect(jsonPath("$.allergies").value(""));

		verify(medicalRecordServiceMock).saveMedicalRecord(medicalRecordMock1);

	}

	@Test
	public void updateMedicalRecordTest() throws Exception {

		MedicalRecord medicalRecordMock1 = mockMedicalRecord1();

		Optional<MedicalRecord> medicalRecordOptionalMock1 = Optional.ofNullable(medicalRecordMock1);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(medicalRecordMock1);

		when(medicalRecordServiceMock.updateMedicalRecord(id1, medicalRecordMock1))
				.thenReturn(medicalRecordOptionalMock1);

		mockMvc.perform(put("/medicalRecord/{id}", id1).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("Ron")).andExpect(jsonPath("$.lastName").value("Peters"))
				.andExpect(jsonPath("$.birthdate").value("04/06/1965")).andExpect(jsonPath("$.medications").value(""))
				.andExpect(jsonPath("$.allergies").value(""));

		verify(medicalRecordServiceMock).updateMedicalRecord(id1, medicalRecordMock1);

	}

	@Test
	public void updateMedicalRecordNotFoundTest() throws Exception {

		MedicalRecord medicalRecordMock = mockMedicalRecord();

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(medicalRecordMock);

		mockMvc.perform(put("/medicalRecord/{id}", id1).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isNotFound());

		verify(medicalRecordServiceMock).updateMedicalRecord(id1, medicalRecordMock);

	}
}
