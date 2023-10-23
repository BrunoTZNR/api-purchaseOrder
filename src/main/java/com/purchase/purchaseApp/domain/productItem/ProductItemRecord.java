package com.purchase.purchaseApp.domain.productItem;

import jakarta.validation.constraints.NotNull;

public record ProductItemRecord(
		
		@NotNull
		Integer quantity,
		
		@NotNull
		Double amount,
		
		@NotNull
		Integer productId,
		
		@NotNull
		Integer purchaseOrderId
		) {

}
