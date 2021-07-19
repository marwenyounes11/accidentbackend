package io.oa.accidentincident.jwtauthentication.message.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class DroitForm {
	private Long id;	
	private String action;
	private Boolean checked;
	

	public DroitForm(String action, Boolean checked) {
		this.action = action;
		this.checked = checked;
	}

	

	
	
}
