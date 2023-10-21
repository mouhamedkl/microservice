package tn.esprit.assurance.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.assurance.entities.Assurance;
import tn.esprit.assurance.entities.Beneficiaire;
import tn.esprit.assurance.entities.Contrat;
import tn.esprit.assurance.entities.TypeContrat;
import tn.esprit.assurance.repositories.IAssuranceRepository;
import tn.esprit.assurance.repositories.IBeneficiaireRepository;
import tn.esprit.assurance.repositories.IContratRepository;


import javax.transaction.Transactional;
import java.util.*;
import java.util.Map.Entry;

@Slf4j
@Service
public class BeneficiaireServicesImpl implements IBeneficiaireServices {

	@Autowired
	IBeneficiaireRepository benefRepository;

	@Autowired
	IAssuranceRepository assuranceRepository;

	@Autowired
	IContratRepository contratRepository;

	@Override
	public int ajouterBeneficaire(Beneficiaire bf) {
		return benefRepository.save(bf).getCin();
	}

	@Transactional
	@Override
	public int ajouterAssurance(Assurance a, int cinBf) {
		Beneficiaire beneficiaire = benefRepository.getByCin(cinBf);
		a.setBeneficiaire(beneficiaire);
		Contrat c = contratRepository.save(a.getContrat());
		a.setContrat(c);
		return assuranceRepository.save(a).getIdAssurance();
	}



	@Override
	public Contrat getContratBf(int idBf) {
		Beneficiaire beneficiaire = benefRepository.findById(idBf).orElse(null);

		Contrat contrat = beneficiaire.getAssurances().get(0).getContrat();
		Date oldDate = contrat.getDateEffet();

		for (int i = 1; i < beneficiaire.getAssurances().size(); i++) {
			Assurance assurance = beneficiaire.getAssurances().get(i);
			if (oldDate.after(assurance.getContrat().getDateEffet())) {
				contrat = assurance.getContrat();
			}
		}
		return contrat;
	}

	@Override
	public float getMontantBf(int cinBf) {
		Beneficiaire beneficiaire = benefRepository.getByCin(cinBf);
		float montantContrat = 0;
		for (Assurance ass : beneficiaire.getAssurances()) {
			if (ass.getContrat().getType() == TypeContrat.Mensuel) {
				montantContrat += ass.getMontant() * 12;
			} else if (ass.getContrat().getType() == TypeContrat.Semestriel) {
				montantContrat += ass.getMontant() * 2;
			} else {
				montantContrat += ass.getMontant();
			}
		}
		/* Methode 1 : JPQL pour annuel seulement */
		// return
		// assuranceRepository.getMontantAnnuelByBf(beneficiaire.getIdBeneficiaire());

		return montantContrat;
	}

	@Override
	public Set<Beneficiaire> getBeneficairesAsC(TypeContrat tc) {
		return benefRepository.getByAssuranceType(tc);
	}

	@Override
	@Scheduled(cron = "*/60 * * * * *")
	public void statistiques() {

		TreeMap<Integer, Integer> myStat = new TreeMap<>(Collections.reverseOrder());

		for (Beneficiaire b : benefRepository.findAll()) {
			myStat.put(b.getAssurances().size(), b.getCin());
		}
		for (Entry<Integer, Integer> va : myStat.entrySet()) {
			log.info(va.getKey() + "|" + va.getValue());
		}
	}

}
