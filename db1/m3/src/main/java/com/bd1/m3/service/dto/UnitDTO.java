package com.bd1.m3.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UnitDTO {
    private Long id;

    private Long unitFloor;
    private Long unitNumber;
    private String block;

    public Long getUnitFloor() {
        return unitFloor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUnitFloor(Long unitFloor) {
        this.unitFloor = unitFloor;
    }

    public Long getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(Long unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}
