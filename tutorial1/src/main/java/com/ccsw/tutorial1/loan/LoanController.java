package com.ccsw.tutorial1.loan;


import com.ccsw.tutorial1.loan.model.Loan;
import com.ccsw.tutorial1.loan.model.LoanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/loan")
@CrossOrigin(origins = "*")


public class LoanController {
    @Autowired
    LoanService loanService;

    @GetMapping
    public Page<Loan> find (
            Pageable pageable,
            @RequestParam(required = false) Long gameId,
            @RequestParam(required = false) Long clientId,
            @RequestParam(required = false) LocalDate date) {
        return  this.loanService.find(pageable, gameId, clientId, date);
    }


    @PutMapping({"","/{id}"})
    public void save(@PathVariable(required = false) Long id,
                     @RequestBody LoanDto dto) {
        this.loanService.save(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.loanService.delete(id);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handlerIllegalArgumentException(IllegalArgumentException ex){
        return Map.of("message", ex.getMessage());
    }
}


