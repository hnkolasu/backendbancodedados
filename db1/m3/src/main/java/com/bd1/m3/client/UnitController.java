package com.bd1.m3.client;

import com.bd1.m3.service.UnitService;
import com.bd1.m3.service.dto.DebtorDTO;
import com.bd1.m3.service.dto.UnitDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/unit")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createUnit(@RequestBody UnitDTO unitDTO) {
        unitService.createUnit(unitDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public ResponseEntity<List<UnitDTO>> find(){
        return ResponseEntity.ok(unitService.findUnits());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        unitService.delete(id);
        return ResponseEntity.ok().build();
    }
}
