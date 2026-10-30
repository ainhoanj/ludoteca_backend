package com.ccsw.tutorial1.loan;

import com.ccsw.tutorial1.loan.model.Loan;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

public class LoanSpecification {

    public static Specification<Loan> gameIdEqual(Long gameId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("game").get("id"), gameId);
    }


    public static  Specification<Loan> clientIdEqual(Long clientId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("client").get("id"), clientId);
    }

    public static  Specification<Loan> activeOnDate(LocalDate date) {
        return (root,query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), date),
                        criteriaBuilder.greaterThanOrEqualTo(root.get("endDate"), date)
                );
    }
}
