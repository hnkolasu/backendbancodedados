package com.bd1.m3.client;

import com.bd1.m3.service.DebtorService;
import com.bd1.m3.service.dto.DebtorDTO;
import com.bd1.m3.service.dto.PaymentResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/debtor")
public class DebtorController {

    private final DebtorService debtorService;

    public DebtorController(DebtorService debtorService) {
        this.debtorService = debtorService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createUnit(@RequestBody DebtorDTO debtorDTO) {
        debtorService.createDebtor(debtorDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public ResponseEntity<List<DebtorDTO>> find(){
        return ResponseEntity.ok(debtorService.findDebtors());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        debtorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
