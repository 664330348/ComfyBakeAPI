package com.revature.comfybake.Bake.dtos;

import java.util.Arrays;

public class PurchaseRequest {
    private PurchaseItem[] purchaseItems;

    public PurchaseRequest() {
        super();
    }

    public PurchaseRequest(PurchaseItem[] purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    public PurchaseItem[] getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(PurchaseItem[] purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    @Override
    public String toString() {
        return "PurchaseRequest{" +
                "purchaseItems=" + Arrays.toString(purchaseItems) +
                '}';
    }
}
