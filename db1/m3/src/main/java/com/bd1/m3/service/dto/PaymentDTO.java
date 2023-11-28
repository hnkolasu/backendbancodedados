package com.bd1.m3.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Timestamp;

public class PaymentDTO {
    private Long id;
    private Long debtorId;
    private Timestamp paymentDate;
    private MultipartFile note;
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

    public Long getDebtorId() {
        return debtorId;
    }

    public void setDebtorId(Long debtorId) {
        this.debtorId = debtorId;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    public MultipartFile getNote() {
        return note;
    }

    public void setNote(MultipartFile note) {
        this.note = note;
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
