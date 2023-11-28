package com.bd1.m3.service;

import com.bd1.m3.config.DatabaseConfig;
import com.bd1.m3.service.dto.DebtorDTO;
import com.bd1.m3.service.dto.UnitDTO;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UnitService {

    public void createUnit(UnitDTO unit) {
        Connection c = DatabaseConfig.getConnection();
        try{
            CallableStatement callProcedure = c.prepareCall("{call InsertUnit(?, ?, ?)}");
            callProcedure.setLong(1, unit.getUnitFloor());
            callProcedure.setLong(2, unit.getUnitNumber());
            callProcedure.setString(3, unit.getBlock());

            callProcedure.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error when connecting to database.", e);
        }
    }

    public List<UnitDTO> findUnits() {
        List<UnitDTO> unitDTOS = new ArrayList<>();
        String query = "select id, unit_floor, unit_number, block from unit";
        Connection c = DatabaseConfig.getConnection();
        try{
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                UnitDTO unitDTO = new UnitDTO();
                unitDTO.setId(rs.getLong("id"));
                unitDTO.setUnitFloor(rs.getLong("unit_floor"));
                unitDTO.setUnitNumber(rs.getLong("unit_number"));
                unitDTO.setBlock(rs.getString("block"));
                unitDTOS.add(unitDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding debtors.", e);
        }
        return unitDTOS;
    }

    public void delete(Long id) {
        String query = "delete from unit where id = ? ";
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
