package com.ccsw.tutorial1.loan;

import com.ccsw.tutorial1.loan.model.Loan;
import com.ccsw.tutorial1.loan.model.LoanDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

public interface LoanService {

    Page<Loan> find(Pageable pageable, Long gameId, Long clientId, LocalDate date);

    void save(Long id, LoanDto dto);

    void delete(Long id);


}
