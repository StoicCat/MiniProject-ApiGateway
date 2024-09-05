package com.miniProject.OlShop.model.response;

import java.util.List;

import com.miniProject.OlShop.model.request.CreatePurchaseTransactionDetailRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePurchaseTransactionDetailRequestList {
	
	private String purchaseTransactionId;
	private List<CreatePurchaseTransactionDetailRequest> details;

}
