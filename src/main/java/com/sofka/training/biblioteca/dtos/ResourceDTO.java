package com.sofka.training.biblioteca.dtos;

import java.time.LocalDate;

public class ResourceDTO {

    private String id;
    private String name;
    private String kind;
    private String thematic;
    private Integer quantityAvailable;
    private Integer amountBorrowed;
    private LocalDate localDate;

    public ResourceDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getThematic() {
        return thematic;
    }

    public void setThematic(String thematic) {
        this.thematic = thematic;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Integer getAmountBorrowed() {
        return amountBorrowed;
    }

    public void setAmountBorrowed(Integer amountBorrowed) {
        this.amountBorrowed = amountBorrowed;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
