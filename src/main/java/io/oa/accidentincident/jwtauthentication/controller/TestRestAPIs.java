
package io.oa.accidentincident.jwtauthentication.controller;
 import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestAPIs {
	
	@GetMapping("/api/test/chef")
	@PreAuthorize("hasRole('CHEF') or hasRole('ADMIN')")
	public String chefAccess() {
		return ">>> User Contents!";
	}

	@GetMapping("/api/test/pcr")
	@PreAuthorize("hasRole('PCR') or hasRole('ADMIN')")
	public String agentPcrAccess() {
		return ">>> Agent Pcr Contents";
	}
	
	@GetMapping("/api/test/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return ">>> Admin Contents";
	}
	
	@GetMapping("/api/test/chefIncendie")
	@PreAuthorize("hasRole('CHEF_INCENDIE') or hasRole('ADMIN')")
	public String chefIncendieAccess() {
		return ">>> Chef Incendie Contents";
	}
	
	@GetMapping("/api/test/agentIncendie")
	@PreAuthorize("hasRole('AGENT_INCENDIE') or hasRole('ADMIN')")
	public String agentIncendieAccess() {
		return ">>> Agent Incendie Contents";
	}
}
