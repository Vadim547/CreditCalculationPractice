package com.example.CreditCalulation.service;

import com.example.CreditCalulation.model.CreditRequestDto;
import com.example.CreditCalulation.model.CreditResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class CreditService {
    public ResponseEntity<CreditResponseDto> calculateLoan(CreditRequestDto creditRequestDto) {
        CreditResponseDto creditResponseDto = new CreditResponseDto(abilityToPay(creditRequestDto),creditRequestDto.getLoanType(), ZonedDateTime.now(),interestCalculate(creditRequestDto));
        return new ResponseEntity<>(creditResponseDto, HttpStatus.OK);
    }

    private List<Map<String, BigDecimal>> interestCalculate(CreditRequestDto creditRequestDto) {
        List<Map<String, BigDecimal>> listOfStatements = new LinkedList<>();
        double loan = creditRequestDto.getLoanAmount();
        double mainDebt = creditRequestDto.getLoanAmount() / creditRequestDto.getTerm();
        double statement;
        for (int count = 0; count < creditRequestDto.getTerm(); count++) {
            System.out.println(mainDebt);
            Map<String, BigDecimal> statementObject = new HashMap<>();
            statement = (loan * 0.13 / 12);
            statementObject.put("Remain to pay", new BigDecimal(loan).setScale(2,RoundingMode.CEILING));
            statementObject.put("Main Debt", new BigDecimal(mainDebt).setScale(2,RoundingMode.CEILING));
            statementObject.put("Interest mothly", new BigDecimal(statement).setScale(2,RoundingMode.CEILING));
            listOfStatements.add(statementObject);
            loan = loan - mainDebt;
        }
        return listOfStatements;
    }

    public boolean abilityToPay(CreditRequestDto creditRequestDto) {
       double statement = creditRequestDto.getLoanAmount() * 0.13 / 12;
       double mainDebt = creditRequestDto.getLoanAmount() / creditRequestDto.getTerm();
       return creditRequestDto.getSalary() > statement + mainDebt;
    }
}


