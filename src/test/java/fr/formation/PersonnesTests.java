package fr.formation;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

import fr.formation.dal.PersonneDao;
import fr.formation.model.Adresse;
import fr.formation.model.Personne;

@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
class PersonnesTests {

	@Autowired
	PersonneDao pDao;

	@Test
	@DatabaseSetup("classpath:personnesInit.xml")
	public void testFind() throws Exception {
		List<Personne> personList = pDao.findAll();
		Assertions.assertEquals(3, personList.size());
		Assertions.assertEquals("Marie", personList.get(0).getPrenom());

	}

	@Test
	@DatabaseSetup("classpath:personnesInit.xml")
	@ExpectedDatabase(value = "classpath:personnesApresRemove.xml", table = "Personne")
	public void testRemove() throws Exception {
		pDao.deleteById(14);
	}

	@Test
	@DatabaseSetup("classpath:personnesInit.xml")
	@ExpectedDatabase(value = "classpath:personnesApresAjout.xml", table = "Personne")
	public void testAjout() throws Exception {
		pDao.save(new Personne("Lerouge", "Seb", 43, new Adresse("72000", "Le Mans")));
	}

	@Test
	@DatabaseSetup("classpath:personnesInit.xml")
	@ExpectedDatabase(value = "classpath:personnesApresUpdate.xml", table = "Personne")
	public void testUpdate() throws Exception {

		Personne p = pDao.findById(16).get();
		p.setNom("Leviolet");
		p.setPrenom("Aline");

		pDao.save(p);
	}

}
