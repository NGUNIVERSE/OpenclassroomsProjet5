package com.openclassrooms.safetyAlertApi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.openclassrooms.safetyAlertApi.model.MedicalRecord;
import com.openclassrooms.safetyAlertApi.repository.MedicalRecordRepository;

public class MedicalRecordServiceTest {

	@Mock
	private MedicalRecordRepository medicalRecordRepositoryMock;

	@InjectMocks
	private MedicalRecordService medicalRecordService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

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

		when(medicalRecordRepositoryMock.findById(id1)).thenReturn(medicalRecordOptionalMock);
		when(medicalRecordRepositoryMock.findById(id2)).thenReturn(medicalRecordOptionalMock1);

		Optional<MedicalRecord> result = medicalRecordService.getMedicalRecord(id1);
		Optional<MedicalRecord> result1 = medicalRecordService.getMedicalRecord(id2);

		assertThat(result).isEqualTo(medicalRecordOptionalMock);
		assertThat(result1).isEqualTo(medicalRecordOptionalMock1);

		verify(medicalRecordRepositoryMock).findById(id1);
		verify(medicalRecordRepositoryMock).findById(id2);

	}

	@Test
	public void testGetMedicalRecords() throws Exception {

		MedicalRecord medicalRecordMock = mockMedicalRecord();
		MedicalRecord medicalRecordMock1 = mockMedicalRecord1();

		List<MedicalRecord> listOfMedicalRecord = new ArrayList<>();
		listOfMedicalRecord.add(medicalRecordMock);
		listOfMedicalRecord.add(medicalRecordMock1);
		Iterable<MedicalRecord> listOfMedicalRecordTest = listOfMedicalRecord;

		when(medicalRecordRepositoryMock.findAll()).thenReturn(listOfMedicalRecordTest);

		Iterable<MedicalRecord> result = medicalRecordService.getMedicalRecords();

		assertThat(result).isEqualTo(listOfMedicalRecordTest);
		verify(medicalRecordRepositoryMock).findAll();

	}

	@Test
	public void testDeleteMedicalRecord() throws Exception {

		medicalRecordService.deleteMedicalRecord(id1);

		verify(medicalRecordRepositoryMock).deleteById(id1);

	}

	@Test
	public void testGetMedicalRecordUnknownId() throws Exception {

		medicalRecordService.deleteMedicalRecord(id3);

		verify(medicalRecordRepositoryMock).deleteById(id3);

	}

	@Test
	public void testSaveMedicalRecord() throws Exception {

		MedicalRecord medicalRecordMock = mockMedicalRecord();

		when(medicalRecordRepositoryMock.save(medicalRecordMock)).thenReturn(medicalRecordMock);

		MedicalRecord result = medicalRecordService.saveMedicalRecord(medicalRecordMock);

		verify(medicalRecordRepositoryMock).save(medicalRecordMock);
		assertThat(result).isEqualTo(medicalRecordMock);
	}

	@Test
	public void updateMedicalRecordTest() throws Exception {

		MedicalRecord medicalRecordMock = mockMedicalRecord();

		Optional<MedicalRecord> medicalRecordOptionalMock = Optional.ofNullable(medicalRecordMock);

		when(medicalRecordRepositoryMock.findById(id1)).thenReturn(medicalRecordOptionalMock);
		when(medicalRecordRepositoryMock.save(medicalRecordMock)).thenReturn(medicalRecordMock);

		Optional<MedicalRecord> result = medicalRecordService.updateMedicalRecord(id1, medicalRecordMock);

		verify(medicalRecordRepositoryMock).findById(id1);
		verify(medicalRecordRepositoryMock).save(medicalRecordMock);
		assertThat(result).isEqualTo(medicalRecordOptionalMock);
	}

	@Test
	public void updateMedicalRecordNotFoundTest() throws Exception {

		MedicalRecord medicalRecordMock = mockMedicalRecord();

		when(medicalRecordRepositoryMock.findById(id3)).thenReturn(Optional.empty());

		Optional<MedicalRecord> result = medicalRecordService.updateMedicalRecord(id3, medicalRecordMock);

		verify(medicalRecordRepositoryMock).findById(id3);
		verify(medicalRecordRepositoryMock, times(0)).save(medicalRecordMock);
		assertThat(result).isEqualTo(Optional.empty());
	}

	@Test
	public void findMedicalRecordByFirstnameAndLastnameTest() {
		MedicalRecord medicalRecordMock = mockMedicalRecord();
		String firstname = "Reginold";
		String lastname = "Walker";

		when(medicalRecordRepositoryMock.findMedicalRecordByFirstnameAndLastname(firstname, lastname))
				.thenReturn(medicalRecordMock);

		MedicalRecord result = medicalRecordService.findMedicalRecordByFirstnameAndLastname(firstname, lastname);

		verify(medicalRecordRepositoryMock).findMedicalRecordByFirstnameAndLastname(firstname, lastname);
		assertThat(result).isEqualTo(medicalRecordMock);
	}

}
