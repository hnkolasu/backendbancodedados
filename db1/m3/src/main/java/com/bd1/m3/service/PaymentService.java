package com.bd1.m3.service;

import com.bd1.m3.config.DatabaseConfig;
import com.bd1.m3.service.dto.PaymentDTO;
import com.bd1.m3.service.dto.PaymentResponseDTO;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    public void createPayment(PaymentDTO paymentDTO) {
        Connection c = DatabaseConfig.getConnection();
        try{
            CallableStatement callProcedure = c.prepareCall("{call InsertPayment(?, ?, ?, ?, ?, ?)}");
            callProcedure.setLong(1, paymentDTO.getDebtorId());
            callProcedure.setTimestamp(2, paymentDTO.getPaymentDate());
            callProcedure.setBlob(3, paymentDTO.getNote().getInputStream());
            callProcedure.setLong(4, paymentDTO.getReferenceYear());
            callProcedure.setLong(5, paymentDTO.getReferenceMonth());
            callProcedure.setLong(6, paymentDTO.getUnitId());

            callProcedure.execute();
        } catch (Exception e) {
            throw new RuntimeException("Error when creating payment.", e);
        }
    }

    public Object download(Long id) {
        String query = "select note from payment where id = ? ";
        Connection c = DatabaseConfig.getConnection();
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Blob blob = rs.getBlob("note");
                return blob.getBytes(1, (int) blob.length());
            }
        }catch (Exception e) {
            throw new RuntimeException("Error when downloading note.", e);
        }
        return null;
    }

    public List<PaymentResponseDTO> getPayments() {
        List<PaymentResponseDTO> paymentResponseDTOList = new ArrayList<>();
        String query = "select p.id, d.name, p.payment_date, p.reference_year, p.reference_month, " +
                "p.unit_id, p.register_date from payment p " +
                "inner join debtor d on d.id = p.debtor_id " +
                "order by p.reference_year, p.reference_month asc ";
        Connection c = DatabaseConfig.getConnection();
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
                paymentResponseDTO.setId(rs.getLong("id"));
                paymentResponseDTO.setDebtorName(rs.getString("name"));
                paymentResponseDTO.setPaymentDate(rs.getTimestamp("payment_date"));
                paymentResponseDTO.setReferenceYear(rs.getLong("reference_year"));
                paymentResponseDTO.setReferenceMonth(rs.getLong("reference_month"));
                paymentResponseDTO.setUnitId(rs.getLong("unit_id"));
                paymentResponseDTO.setRegisterDate(rs.getTimestamp("register_date"));
                paymentResponseDTOList.add(paymentResponseDTO);
            }
        }catch (Exception e) {
            throw new RuntimeException("Error finding payments.", e);
        }
        return paymentResponseDTOList;
    }

    public void delete(Long id) {
        String query = "delete from payment where id = ? ";
        Connection c = DatabaseConfig.getConnection();
        try{
            PreparedStatement ps = c.prepareStatement(query);
            ps.setLong(1, id);
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException("Error when deleting payment.", e);
        }
    }
}
