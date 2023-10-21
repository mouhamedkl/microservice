package tn.esprit.assurance.services;

import tn.esprit.assurance.entities.Assurance;
import tn.esprit.assurance.entities.Beneficiaire;
import tn.esprit.assurance.entities.Contrat;
import tn.esprit.assurance.entities.TypeContrat;


import java.util.List;
import java.util.Set;

public interface IBeneficiaireServices {

	int ajouterBeneficaire(Beneficiaire bf);

	int ajouterAssurance(Assurance a, int cinBf);
	Contrat getContratBf(int idBf);

	float getMontantBf(int cinBf);

	Set<Beneficiaire> getBeneficairesAsC(TypeContrat tc);

	void statistiques();
}
