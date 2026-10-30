package com.ccsw.tutorial1.loan;

import com.ccsw.tutorial1.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;


public interface LoanRepository extends JpaRepository<Loan, Long>,
JpaSpecificationExecutor<Loan> {

    boolean existsByGameIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndIdNot(
            Long gameId,
            LocalDate endDate,
            LocalDate startDate,
            Long id
    );

    long countByClientIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndIdNot(
            Long clientId,
            LocalDate endDate,
            LocalDate startDate,
            Long id
    );
}
