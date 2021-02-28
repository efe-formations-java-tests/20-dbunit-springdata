package fr.formation.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Personne;

public interface PersonneDao extends JpaRepository<Personne, Integer>{

}
