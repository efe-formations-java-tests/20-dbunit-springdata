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

import fr.formation.dal.LivreDao;
import fr.formation.model.Livre;

@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
class LivreTests {

	@Autowired
	LivreDao lDao;

	@Test
	@DatabaseSetup("classpath:livresInit.xml")
	public void testFind() throws Exception {
		List<Livre> listeL = lDao.findAll();
		Assertions.assertEquals(3, listeL.size());
		Assertions.assertEquals("Baudelaire", listeL.get(0).getAuteur());
		for (Livre livre : listeL) {
			System.out.println("  - " + livre);
		}
	}

	
	@Test
	@DatabaseSetup("classpath:livresInit.xml")
	@ExpectedDatabase(value="classpath:livresApresRemove.xml", table = "Livre")
	public void testRemove() throws Exception {
		lDao.deleteById(14);
	}

	
	@Test
	@DatabaseSetup("classpath:livresInit.xml")
	@ExpectedDatabase(value="classpath:livresApresAjout.xml", table = "Livre")
	public void testAjout() throws Exception {
		lDao.save(new Livre("Hernani", "Victor Hugo", 232));
	}
}
