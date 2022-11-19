package com.example.cp17312_nhom6_duan1.dto;

public class ServicesDTO {
    private int servicesId;
    private String servicesName;
    private float servicesPrice;
    private int categoriesId;

    public ServicesDTO(int servicesId, String servicesName, float servicesPrice, int categoriesId) {
        this.servicesId = servicesId;
        this.servicesName = servicesName;
        this.servicesPrice = servicesPrice;
        this.categoriesId = categoriesId;
    }

    public ServicesDTO() {
    }

    public int getServicesId() {
        return servicesId;
    }

    public void setServicesId(int servicesId) {
        this.servicesId = servicesId;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public float getServicesPrice() {
        return servicesPrice;
    }

    public void setServicesPrice(float servicesPrice) {
        this.servicesPrice = servicesPrice;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }
}
