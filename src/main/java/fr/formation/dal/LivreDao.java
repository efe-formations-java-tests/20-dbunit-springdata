package fr.formation.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Livre;

public interface LivreDao extends JpaRepository<Livre, Integer>{

}
