package com.purchase.purchaseApp.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecord(
		@NotBlank
		String name,
		
		@NotBlank
		String description,
		
		@NotNull
		Integer quantity,
		
		@NotNull
		Double amount
		) {}