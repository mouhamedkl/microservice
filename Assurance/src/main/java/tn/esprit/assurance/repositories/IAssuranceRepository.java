package tn.esprit.assurance.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.assurance.entities.Assurance;
import tn.esprit.assurance.entities.TypeContrat;

public interface IAssuranceRepository extends CrudRepository<Assurance, Integer>{
	
	@Query("Select SUM(a.montant) From Assurance a where a.beneficiaire.idBeneficiaire = :id ")
	float getMontantAnnuelByBf(@Param("id")int idBf);
	
	@Query("Select SUM(a.montant) From Assurance a where a.contrat.type = :typeC")
	float getSumByType(@Param("typeC") TypeContrat typeContrat);

}
