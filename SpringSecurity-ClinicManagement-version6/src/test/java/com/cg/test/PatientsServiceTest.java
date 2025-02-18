package com.cg.test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import com.cg.entity.Patient;
import com.cg.exception.PatientNotFoundException;
import com.cg.repository.PatientRepository;
import com.cg.service.IPatientService;
import com.cg.service.PatientService;
public class PatientsServiceTest {
	
	@Mock
	PatientRepository patientRepository;
	
	@InjectMocks
	PatientService patientService;
	
	private Patient patient1;
	private Patient patient2;
	private List<Patient> plist;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		
		patient1=new Patient();
		patient1.setPatientId(1);
		patient1.setPname("Menakshi");
		patient1.setAge(22);
		patient1.setGender("female");
		patient1.setPhone("7058155906");
		patient1.setEmail("menakshi@gmail.com");
		patient1.setAddress("Solapur-Maharashtra");
		
		patient2=new Patient();
		patient2.setPatientId(2);
		patient2.setPname("Divya");
		patient2.setAge(25);
		patient2.setGender("female");
		patient2.setPhone("7058155906");
		patient2.setEmail("divya@gmail.com");
		patient2.setAddress("Pune-Maharashtra");
		
		plist=new ArrayList<>();
		plist.add(patient1);
		plist.add(patient2);
	}
	
	@Test
	 void findAllPatient() {
		when(patientRepository.findAll()).thenReturn(plist);
		
		List<Patient> result=patientService.findAllPatient();
		
		Assertions.assertEquals(2,result.size());
		Assertions.assertTrue(plist.contains(patient1));
		Assertions.assertTrue(plist.contains(patient2));
		Assertions.assertEquals("Menakshi",result.get(0).getPname());
		Assertions.assertEquals("Divya",result.get(1).getPname());
		
		
	}
	
	@Test
	void findPatientById() {
		
		when(patientRepository.findById(1)).thenReturn(Optional.of(patient1));
		try {
			Patient p=patientService.findPatientById(1);
			Assertions.assertNotNull(p);
			Assertions.assertEquals("Menakshi",p.getPname());
		} catch (PatientNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	@Test
	void deletePatient() {
		when(patientRepository.findById(1)).thenReturn(Optional.of(patient1));
		String result =patientService.deletePatientById(1);
		verify(patientRepository,times(1)).deleteById(1);
		Assertions.assertEquals("patient deleted", result);
	}
	
	@Test
   void testNewPatient() {
     
       when(patientRepository.save(any(Patient.class))).thenReturn(patient1);

     
       Patient result = patientService.createPatient(patient1);

   
       verify(patientRepository, times(1)).save(any(Patient.class));

     
       Assertions.assertNotNull(result);
       Assertions.assertEquals("Menakshi", result.getPname());
   }
	
	@Test
	void updateDoctor() {
		when(patientRepository.findById(1)).thenReturn(Optional.of(patient1));
		
		when(patientRepository.save(patient1)).thenReturn(patient2);
		Patient result;
		try {
			result = patientService.updatePatient(1, patient2);
			verify(patientRepository, times(1)).findById(1);
		    verify(patientRepository, times(1)).save(patient1);
		    Assertions.assertNotNull(result);
		    Assertions.assertEquals("Divya", result.getPname());
		} catch (PatientNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

}
