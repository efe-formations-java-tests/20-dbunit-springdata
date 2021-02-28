package fr.formation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.formation.dal.PersonneDao;
import fr.formation.model.Personne;

//@SpringBootApplication
public class CommandLineApplication implements CommandLineRunner {

	
	@Autowired
	PersonneDao pDao;
	
	public static void main(String[] args) {
		SpringApplication.run(CommandLineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		Personne p = new Personne("Legrand", "Anne", 45);
//		pDao.save(p);
		
		
		System.out.println("Liste des personnes");
		List<Personne> listeP = pDao.findAll();
		for (Personne personne : listeP) {
			System.out.println(personne);
		}
	}

}
