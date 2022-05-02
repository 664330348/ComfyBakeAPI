package com.revature.comfybake.Bake.dtos;

public class PurchaseItem {
    private String bakerProfileId;
    private String bakeId;
    private int quantity;

    public PurchaseItem() {
        super();
    }

    public PurchaseItem(String bakerProfileId, String bakeId, int quantity) {
        this.bakerProfileId = bakerProfileId;
        this.bakeId = bakeId;
        this.quantity = quantity;
    }

    public String getBakerProfileId() {
        return bakerProfileId;
    }

    public void setBakerProfileId(String bakerProfileId) {
        this.bakerProfileId = bakerProfileId;
    }

    public String getBakeId() {
        return bakeId;
    }

    public void setBakeId(String bakeId) {
        this.bakeId = bakeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PurchaseItem{" +
                "bakerProfileId='" + bakerProfileId + '\'' +
                ", bakeId='" + bakeId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
