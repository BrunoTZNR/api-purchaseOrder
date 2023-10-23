package com.purchase.purchaseApp.domain.purchaseOrder;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PurchaseOrderRecord(
		
		@NotNull
		@JsonFormat(pattern = "yyyy-MM-dd")
		LocalDate dataPedido,
		
		@NotBlank
		String clientCpf
		) {}
