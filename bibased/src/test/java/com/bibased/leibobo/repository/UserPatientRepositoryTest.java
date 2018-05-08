package com.bibased.leibobo.repository;

import com.bibased.leibobo.domain.UserPatient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by boboLei on 2018/4/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserPatientRepositoryTest {

	@Autowired
	UserPatientRepository userPatientRepository;

	private UserPatient userPatient;

	@Before
	public void setUp() throws Exception {
		this.userPatient = new UserPatient("bobo","leibobo","1234","1844",1,
				0,"6228263333",34,"18401644236@163.com","gansu");
	}

	@After
	public void tearDown() throws Exception {
		this.userPatient = null;
	}

	@Test
	public void savePatient(){
		userPatientRepository.save(userPatient);
	}

	@Test
	public void listPatient(){
		List<UserPatient> userPatientList = new ArrayList<UserPatient>();
		userPatientList =  userPatientRepository.findAll();
		assertEquals(userPatientList.size(),2);
	}

}