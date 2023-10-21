package tn.esprit.assurance.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.assurance.entities.Beneficiaire;
import tn.esprit.assurance.entities.TypeContrat;


import java.util.Set;

@Repository
public interface IBeneficiaireRepository extends CrudRepository<Beneficiaire, Integer>{

	Beneficiaire getByCin(int cinBenef);
	
	@Query("Select b From Beneficiaire b join b.assurances ass join ass.contrat c where c.type = :tc")
	Set<Beneficiaire> getByAssuranceType(@Param("tc") TypeContrat tc);
	
	
	
}
