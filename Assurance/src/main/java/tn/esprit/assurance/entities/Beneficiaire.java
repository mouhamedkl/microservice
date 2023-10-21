package tn.esprit.assurance.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Beneficiaire implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idBeneficiaire;
	private int cin;
	private String nom;
	private String prenom;
	private String profession;
	private float salaire;
	@JsonIgnore
	@OneToMany(mappedBy="beneficiaire", fetch=FetchType.EAGER)
	private List<Assurance> assurances;
	
}
