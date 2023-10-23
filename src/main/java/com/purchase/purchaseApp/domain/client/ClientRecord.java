package com.purchase.purchaseApp.domain.client;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientRecord(
		
		@NotBlank
		String cpf,
		
		@NotBlank
		String name,
		
		@NotBlank
		String email,
		
		@NotNull
		@JsonFormat(pattern="yyyy-MM-dd")
		LocalDate dtNasc
		) {

}
