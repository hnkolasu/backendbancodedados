package com.bd1.m3.service;

import com.bd1.m3.config.DatabaseConfig;
import com.bd1.m3.service.dto.DebtorDTO;
import com.bd1.m3.service.dto.UnitDTO;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DebtorService {

    public void createDebtor(DebtorDTO debtorDTO) {
        Connection c = DatabaseConfig.getConnection();
        try{
            CallableStatement callProcedure = c.prepareCall("{call InsertDebtor(?, ?, ?, ?)}");
            callProcedure.setString(1, debtorDTO.getDebtorName());
            callProcedure.setString(2, debtorDTO.getMail());
            callProcedure.setLong(3, debtorDTO.getPhone());
            callProcedure.setLong(4, debtorDTO.getDocumentNumber());

            callProcedure.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating debtor.", e);
        }
    }

    public List<DebtorDTO> findDebtors() {
        List<DebtorDTO> debtorDTOS = new ArrayList<>();
        String query = "select id, name, mail, phone, document_number from debtor";
        Connection c = DatabaseConfig.getConnection();
        try{
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                DebtorDTO debtorDTO = new DebtorDTO();
                debtorDTO.setId(rs.getLong("id"));
                debtorDTO.setDebtorName(rs.getString("name"));
                debtorDTO.setMail(rs.getString("mail"));
                debtorDTO.setPhone(rs.getLong("phone"));
                debtorDTO.setDocumentNumber(rs.getLong("document_number"));
                debtorDTOS.add(debtorDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding debtors.", e);
        }
        return debtorDTOS;
    }

    public void delete(Long id) {
        String query = "delete from debtor where id = ? ";
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
