package io.oa.accidentincident.jwtauthentication.message.request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class RoleForm {
	private Long id;	
	 private String name;
	public RoleForm( String name) {
		this.name = name;
	}
	
	
}
