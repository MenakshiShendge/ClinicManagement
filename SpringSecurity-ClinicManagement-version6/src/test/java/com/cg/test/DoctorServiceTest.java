package com.cg.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cg.dto.DoctorDTO;
import com.cg.entity.Doctor;
import com.cg.exception.DoctorNotFoundException;
import com.cg.repository.DoctorRepository;
import com.cg.service.DoctorService;

public class DoctorServiceTest {
	
	@Mock
	private DoctorRepository doctorRepository;
	
	@InjectMocks
	private DoctorService doctorService;
	
	private Doctor doctor1;
	private Doctor doctor2;
	private List<Doctor> dlist;
	
	@BeforeEach
	 void setup() {
		
		MockitoAnnotations.openMocks(this);
		
		doctor1=new Doctor();
		doctor1.setDoctorId(1);
		doctor1.setDname("Menakshi Shendge");
		doctor1.setSpecialization("Family Medicine");
		doctor1.setPhone("7058155906");
		doctor1.setEmail("menakshi@gmail.com");
		doctor1.setAddress("Solapur-Maharashtra");
		doctor1.setConsultationFees(6500.0);
		
		doctor2=new Doctor();
		doctor2.setDoctorId(2);
		doctor2.setDname("Divya Shendge");
		doctor2.setSpecialization("Cardiologist");
		doctor2.setPhone("9370455906");
		doctor2.setEmail("divya@gmail.com");
		doctor2.setAddress("Solapur-Maharashtra");
		doctor2.setConsultationFees(7500.0);
		
		dlist=new ArrayList<>();
		dlist.add(doctor1);
		dlist.add(doctor2);
	}
	
	@Test
	 void findAllDoctors() {
		when(doctorRepository.findAll()).thenReturn(dlist);
		
		List<Doctor> result=doctorService.findAllDoctors();
		
		Assertions.assertEquals(2,result.size());
		Assertions.assertTrue(dlist.contains(doctor1));
		Assertions.assertTrue(dlist.contains(doctor2));
		Assertions.assertEquals("Menakshi Shendge",result.get(0).getDname());
		Assertions.assertEquals("Divya Shendge",result.get(1).getDname());
		
		
	}
	
	@Test
	void findDoctorById() {
		
		when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor1));
		try {
			Doctor d=doctorService.findDoctorById(1);
			Assertions.assertNotNull(d);
			Assertions.assertEquals("Menakshi Shendge",d.getDname());
		} catch (DoctorNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	@Test
	void deleteDoctor() {
		when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor1));
		String result =doctorService.deleteDoctorById(1);
		verify(doctorRepository,times(1)).deleteById(1);
		Assertions.assertEquals("doctor deleted", result);
	}
	
	@Test
    void testNewProduct() {
      
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor1);
 
      
        Doctor result = doctorService.createDoctor(doctor1);
 
    
        verify(doctorRepository, times(1)).save(any(Doctor.class));
 
      
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Menakshi Shendge", result.getDname());
    }
	
	@Test
	void updateDoctor() {
		when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor1));
		
		when(doctorRepository.save(doctor1)).thenReturn(doctor2);
		Doctor result;
		try {
			result = doctorService.updateDoctorById(1, doctor2);
			verify(doctorRepository, times(1)).findById(1);
		    verify(doctorRepository, times(1)).save(doctor1);
		    Assertions.assertNotNull(result);
		    Assertions.assertEquals("Divya Shendge", result.getDname());
		} catch (DoctorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

}
 