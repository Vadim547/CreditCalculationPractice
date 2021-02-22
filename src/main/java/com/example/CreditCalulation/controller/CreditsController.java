package com.example.CreditCalulation.controller;


import com.example.CreditCalulation.model.CreditRequestDto;
import com.example.CreditCalulation.model.CreditResponseDto;
import com.example.CreditCalulation.service.CreditService;
import com.example.CreditCalulation.exception.ApiRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/credit")
public class CreditsController {
    private final CreditService creditService;

    public CreditsController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping("/checkAndCalculate")
    public ResponseEntity<CreditResponseDto> checkAndCalculate(@RequestBody CreditRequestDto creditRequestDto) {
        if (!creditService.abilityToPay(creditRequestDto)) {
            throw new ApiRequestException("You can't afford this kind of loan");
        }
      return creditService.calculateLoan(creditRequestDto);
    }
}
