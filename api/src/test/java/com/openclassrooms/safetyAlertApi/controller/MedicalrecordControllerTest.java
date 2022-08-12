
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
import com.openclassrooms.safetyAlertApi.model.Medicalrecord;
import com.openclassrooms.safetyAlertApi.service.MedicalrecordService;

@WebMvcTest(controllers = MedicalrecordController.class)
public class MedicalrecordControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MedicalrecordService medicalrecordServiceMock;

	/*
	 * @MockBean private Medicalrecord medicalrecordMock;
	 */

	private Medicalrecord mockMedicalrecord() {

		Medicalrecord medicalrecordMock = new Medicalrecord();
		medicalrecordMock.setFirstname("Reginold");
		medicalrecordMock.setLastname("Walker");
		medicalrecordMock.setBirthdate("08/30/1979");
		medicalrecordMock.setMedications(List.of("thradox:700mg"));
		medicalrecordMock.setAllergies(List.of("illisoxian"));
		return medicalrecordMock;
	}

	private Medicalrecord mockMedicalrecord1() {

		Medicalrecord medicalrecordMock1 = new Medicalrecord();
		medicalrecordMock1.setFirstname("Ron");
		medicalrecordMock1.setLastname("Peters");
		medicalrecordMock1.setBirthdate("04/06/1965");
		medicalrecordMock1.setMedications(List.of(""));
		medicalrecordMock1.setAllergies(List.of(""));
		return medicalrecordMock1;
	}

	private long id1 = 0;
	private long id2 = 1;
	private long id3 = 50;

	@Test
	public void testGetMedicalrecord() throws Exception {

		Medicalrecord medicalrecordMock = mockMedicalrecord();
		Medicalrecord medicalrecordMock1 = mockMedicalrecord1();

		Optional<Medicalrecord> medicalrecordOptionalMock = Optional.ofNullable(medicalrecordMock);
		Optional<Medicalrecord> medicalrecordOptionalMock1 = Optional.ofNullable(medicalrecordMock1);

		when(medicalrecordServiceMock.getMedicalrecord(id1)).thenReturn(medicalrecordOptionalMock);
		when(medicalrecordServiceMock.getMedicalrecord(id2)).thenReturn(medicalrecordOptionalMock1);

		mockMvc.perform(get("/medicalRecord/{id}", id1)).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("Reginold")).andExpect(jsonPath("$.lastName").value("Walker"))
				.andExpect(jsonPath("$.birthdate").value("08/30/1979"))
				.andExpect(jsonPath("$.medications").value("thradox:700mg"))
				.andExpect(jsonPath("$.allergies").value("illisoxian"));

		mockMvc.perform(get("/medicalRecord/{id}", id2)).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("Ron")).andExpect(jsonPath("$.lastName").value("Peters"))
				.andExpect(jsonPath("$.birthdate").value("04/06/1965")).andExpect(jsonPath("$.medications").value(""))
				.andExpect(jsonPath("$.allergies").value(""));

		verify(medicalrecordServiceMock).getMedicalrecord(id1);
		verify(medicalrecordServiceMock).getMedicalrecord(id2);

	}

	@Test
	public void testGetMedicalrecords() throws Exception {

		Medicalrecord medicalrecordMock = mockMedicalrecord();
		Medicalrecord medicalrecordMock1 = mockMedicalrecord1();

		List<Medicalrecord> listOfMedicalrecord = new ArrayList<>();
		listOfMedicalrecord.add(medicalrecordMock);
		listOfMedicalrecord.add(medicalrecordMock1);
		Iterable<Medicalrecord> listOfMedicalrecordTest = listOfMedicalrecord;

		when(medicalrecordServiceMock.getMedicalrecords()).thenReturn(listOfMedicalrecordTest);

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

		verify(medicalrecordServiceMock).getMedicalrecords();

	}

	@Test
	public void testDeleteMedicalrecord() throws Exception {

		mockMvc.perform(delete("/medicalRecord/{id}", id2)).andExpect(status().isNoContent());
		verify(medicalrecordServiceMock).deleteMedicalrecord(id2);

	}

	@Test
	public void testGetMedicalrecordUnknownId() throws Exception {

		mockMvc.perform(get("/medicalRecord/{id}", id3)).andExpect(status().isNotFound());
		verify(medicalrecordServiceMock).getMedicalrecord(id3);

	}

	@Test
	public void testCreateMedicalrecord() throws Exception {

		Medicalrecord medicalrecordMock = mockMedicalrecord();
		Medicalrecord medicalrecordMock1 = mockMedicalrecord1();

		List<Medicalrecord> listOfMedicalrecord = new ArrayList<>();
		listOfMedicalrecord.add(medicalrecordMock);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(medicalrecordMock1);
		when(medicalrecordServiceMock.saveMedicalrecord(medicalrecordMock1)).thenReturn(medicalrecordMock1);

		mockMvc.perform(post("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName").value("Ron")).andExpect(jsonPath("$.lastName").value("Peters"))
				.andExpect(jsonPath("$.birthdate").value("04/06/1965")).andExpect(jsonPath("$.medications").value(""))
				.andExpect(jsonPath("$.allergies").value(""));

		verify(medicalrecordServiceMock).saveMedicalrecord(medicalrecordMock1);

	}

	@Test
	public void updateMedicalrecordTest() throws Exception {

		Medicalrecord medicalrecordMock1 = mockMedicalrecord1();

		Optional<Medicalrecord> medicalrecordOptionalMock1 = Optional.ofNullable(medicalrecordMock1);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(medicalrecordMock1);

		when(medicalrecordServiceMock.updateMedicalrecord(id1, medicalrecordMock1))
				.thenReturn(medicalrecordOptionalMock1);

		mockMvc.perform(put("/medicalRecord/{id}", id1).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("Ron")).andExpect(jsonPath("$.lastName").value("Peters"))
				.andExpect(jsonPath("$.birthdate").value("04/06/1965")).andExpect(jsonPath("$.medications").value(""))
				.andExpect(jsonPath("$.allergies").value(""));

		verify(medicalrecordServiceMock).updateMedicalrecord(id1, medicalrecordMock1);

	}

	@Test
	public void updateMedicalrecordNotFoundTest() throws Exception {

		Medicalrecord medicalrecordMock = mockMedicalrecord();

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(medicalrecordMock);

		mockMvc.perform(put("/medicalRecord/{id}", id1).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isNotFound());

		verify(medicalrecordServiceMock).updateMedicalrecord(id1, medicalrecordMock);

	}
}
