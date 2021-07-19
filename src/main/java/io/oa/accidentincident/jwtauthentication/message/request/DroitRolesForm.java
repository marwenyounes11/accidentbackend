package io.oa.accidentincident.jwtauthentication.message.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class DroitRolesForm {
	private Long id;	
	private Long iddroit;
	private Long idsousrubrique;
	private Long idrole;

	public DroitRolesForm( Long iddroit, Long idsousrubrique, Long idrole) {
		this.iddroit = iddroit;
		this.idsousrubrique = idsousrubrique;
		this.idrole = idrole;
	
	}
	
	

	

	
	
}
