
  package io.oa.accidentincident.jwtauthentication.message.request;
  
 
  
  import java.util.Set;
  
  import javax.validation.constraints.*;
   
  public class SignUpForm {
      @NotBlank
      @Size(min = 3, max = 50)
      private String name;
   
      @NotBlank
      @Size(min = 3, max = 50)
      private String username;
   
      @NotBlank
      @Size(max = 60)
      @Email
      private String email;
      
      @NotBlank
      @Size(max = 60)
      @Email
      private String confirmEmail; 
      
      @NotBlank
      @Size(min=6, max = 100)
      private String password;
      
      @NotBlank
      @Size(min=6, max = 100)
      private String confirmPassword;
      
      private Long idrole;
      
      public String getName() {
          return name;
      }
   
      public void setName(String name) {
          this.name = name;
      }
   
      public String getUsername() {
          return username;
      }
   
      public void setUsername(String username) {
          this.username = username;
      }
   
      public String getEmail() {
          return email;
      }
   
      public void setEmail(String email) {
          this.email = email;
      }
   
      public String getConfirmEmail() {
		return confirmEmail;
	}

	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	public String getPassword() {
          return password;
      }
   
      public void setPassword(String password) {
          this.password = password;
      }

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Long getIdrole() {
		return idrole;
	}

	public void setIdrole(Long idrole) {
		this.idrole = idrole;
	}
      
     
  }
 