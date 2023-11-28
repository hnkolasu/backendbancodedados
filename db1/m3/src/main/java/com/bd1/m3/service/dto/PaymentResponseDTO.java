package com.bd1.m3.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

public class PaymentResponseDTO {
    private Long id;
    private String debtorName;
    private Timestamp paymentDate;
    private Long referenceYear;
    private Long referenceMonth;
    private Long unitId;
    private Timestamp registerDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDebtorName() {
        return debtorName;
    }

    public void setDebtorName(String debtorName) {
        this.debtorName = debtorName;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getReferenceYear() {
        return referenceYear;
    }

    public void setReferenceYear(Long referenceYear) {
        this.referenceYear = referenceYear;
    }

    public Long getReferenceMonth() {
        return referenceMonth;
    }

    public void setReferenceMonth(Long referenceMonth) {
        this.referenceMonth = referenceMonth;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }
}
