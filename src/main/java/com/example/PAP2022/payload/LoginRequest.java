package com.example.PAP2022.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {
	@NotBlank
  	private String email;
	@NotBlank
	private String password;
}
