package tn.esprit.assurance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.assurance.entities.Assurance;
import tn.esprit.assurance.entities.Beneficiaire;
import tn.esprit.assurance.entities.Contrat;
import tn.esprit.assurance.entities.TypeContrat;
import tn.esprit.assurance.repositories.IContratRepository;
import tn.esprit.assurance.services.IBeneficiaireServices;


import java.util.Set;



@RestController
@RequestMapping("/assurance")
public class BeneficiaireRestController {
	
	@Autowired
	IBeneficiaireServices benefServices;
	IContratRepository contratRepository;
	
	@PostMapping("/addBenef")
	@ResponseBody
	public int addBeneficiaire(@RequestBody Beneficiaire beneficiaire){
		
		return benefServices.ajouterBeneficaire(beneficiaire);
	}
	@PostMapping("/addAssurance/{cin-benef}")
	@ResponseBody
	public int addAssurance(@RequestBody Assurance assurance, @PathVariable("cin-benef") int cinB){
		return benefServices.ajouterAssurance(assurance, cinB);
	}
	@GetMapping("/getContratBf/{id-benef}")
	@ResponseBody
	public Contrat getContratByBf(@PathVariable("id-benef")int idBf){
		return benefServices.getContratBf(idBf);
	}

	@GetMapping("/getMontantBf/{cin-benef}")
	public float getMontantBf(@PathVariable("cin-benef") int cinBf){
		return benefServices.getMontantBf(cinBf);
	}
	@GetMapping("/getBeneficairesAsC/{typeC}")
	public Set<Beneficiaire> getBeneficairesAsC(@PathVariable("typeC") TypeContrat tc) {
		return benefServices.getBeneficairesAsC(tc);
	}

}
