package com.bibased.leibobo.repository;

import com.bibased.leibobo.domain.UserSuper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by boboLei on 2018/4/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserSuperRepositoryTest {

	@Autowired
	UserSuperRepository userSuperRepository;

	private UserSuper userSuper;
	private Long id;
	private String passWord;


	@Before
	public void setUp() throws Exception {
		this.userSuper = new UserSuper("bobo","leibobo","123",
				"18401644236",0,0,"622826199509100617");

		this.id = Long.valueOf(2);
		this.passWord = "mimaleibobo";
	}

	@After
	public void tearDown()throws Exception{
		this.userSuper = null;
		this.id = null;
		this.passWord = null;
	}

	@Test
	public void saveUserSuper(){
		userSuperRepository.save(userSuper);
	}

	/*@Test
	public void updatePassWord(){
		userSuperRepository.updatePassword(passWord,id);
	}*/

	/*@Test
	public void selectName(){
		String haha;
		haha = userSuperRepository.selectUserName(id);
		assertEquals(haha,"bobo");
	}
*/
	@Test
	public void findSuperByName(){
		String superName = userSuperRepository.findByUserName("bobo3").getIdNumber();
		assertEquals(superName,"62282633331");
		//assertNotNull(superName);
	}
}