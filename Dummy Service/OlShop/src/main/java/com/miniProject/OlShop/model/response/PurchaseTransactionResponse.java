package com.miniProject.OlShop.model.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseTransactionResponse {
	String id;
	String userId;
	String userFullName;
	String userRole;
	LocalDateTime transactionDate;
	String transactionCode;
}
