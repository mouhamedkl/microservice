package tn.esprit.assurance.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Assurance implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAssurance;
	private String designation;
	private float montant;
	@ManyToOne
	private Contrat contrat;
	@ManyToOne
	private Beneficiaire beneficiaire;
	

}
