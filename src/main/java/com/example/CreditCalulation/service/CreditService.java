package com.example.CreditCalulation.service;

import com.example.CreditCalulation.exception.ApiRequestException;
import com.example.CreditCalulation.model.CreditRequestDto;
import com.example.CreditCalulation.model.CreditResponseDto;
import com.example.CreditCalulation.enums.LoanType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class CreditService {
    public ResponseEntity<CreditResponseDto> calculateLoan(CreditRequestDto creditRequestDto) throws ApiRequestException {
        if (!abilityToPay(creditRequestDto)) {
            throw new ApiRequestException("U can't affort loan");
        }
        CreditResponseDto creditResponseDto = new CreditResponseDto(abilityToPay(creditRequestDto),creditRequestDto.getLoanType(), ZonedDateTime.now(),interestCalculate(creditRequestDto));
        return new ResponseEntity<>(creditResponseDto, HttpStatus.OK);
    }

    private List<Map<String, BigDecimal>> interestCalculate(CreditRequestDto creditRequestDto) {
        List<Map<String, BigDecimal>> listOfStatements = new LinkedList<>();
        double loan = creditRequestDto.getLoanAmount();
        double mainDebt = creditRequestDto.getLoanAmount() / creditRequestDto.getTerm();
        double statement;
        double coefficient = loanTypeInterestCoefficient(creditRequestDto);
        for (int count = 0; count < creditRequestDto.getTerm(); count++) {
            Map<String, BigDecimal> statementObject = new HashMap<>();
            statement = (loan * coefficient / 12);
            statementObject.put("Remain to pay", new BigDecimal(loan).setScale(2,RoundingMode.CEILING));
            statementObject.put("Main Debt", new BigDecimal(mainDebt).setScale(2,RoundingMode.CEILING));
            statementObject.put("Interest mothly", new BigDecimal(statement).setScale(2,RoundingMode.CEILING));
            listOfStatements.add(statementObject);
            loan = loan - mainDebt;
        }
        return listOfStatements;
    }

    private boolean abilityToPay(CreditRequestDto creditRequestDto) {

       double statement = creditRequestDto.getLoanAmount() * loanTypeInterestCoefficient(creditRequestDto) / 12;
       double mainDebt = creditRequestDto.getLoanAmount() / creditRequestDto.getTerm();
       return creditRequestDto.getSalary() > statement + mainDebt;
    }
    private double loanTypeInterestCoefficient(CreditRequestDto creditRequestDto) {
        String loanTypeUpperCased = creditRequestDto.getLoanType().toUpperCase(Locale.ROOT);
        LoanType loanType = LoanType.valueOf(loanTypeUpperCased);
       return loanType.getRate() / 100;
    }

}


