package tn.esprit.assurance.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.assurance.entities.Contrat;
@Repository
public interface IContratRepository extends CrudRepository<Contrat, Integer> {
	
	

	
}
