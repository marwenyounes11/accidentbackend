package io.oa.accidentincident.jwtauthentication.message.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class SousRubriqueForm {
	private Long id;	
	private String libelle;
	private Long idrubrique;
}
