package com.ccsw.tutorial1.loan;

import com.ccsw.tutorial1.game.GameRepository;
import com.ccsw.tutorial1.loan.model.Loan;
import com.ccsw.tutorial1.loan.model.LoanDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import com.ccsw.tutorial1.client.ClientService;
import org.springframework.beans.BeanUtils;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.springframework.data.jpa.domain.Specification;


@Service
@Transactional

public class LoanServiceImpl  implements LoanService{

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    GameRepository gameRepository;

    @Override
    public Page<Loan> find(Pageable pageable, Long gameId, Long clientId, LocalDate date) {
        Specification<Loan> spec = Specification.unrestricted();
        if (gameId != null) {
            spec = spec.and(LoanSpecification.gameIdEqual(gameId));
        }
        if (clientId != null) {
            spec = spec.and(LoanSpecification.clientIdEqual(clientId));

        }
        if (date != null) {
            spec = spec.and(LoanSpecification.activeOnDate(date));
        }

        return this.loanRepository.findAll(spec, pageable);
    }

    @Override
    public void save(Long id, LoanDto dto) {

        Loan loan;

        if (id ==null) {
            loan = new Loan();
        } else {
            loan = this.loanRepository.findById(id).orElse(null);
        }

        BeanUtils.copyProperties(dto, loan, "id", "client", "game");
        loan.setClient(this.clientService.get(dto.getClient().getId()));
        loan.setGame(this.gameRepository.findById(dto.getGame().getId()).orElse(null));

        if (loan.getEndDate().isBefore(loan.getStartDate())) {
            throw new IllegalArgumentException(
                    "La fecha de devolución no puede ser anterior a la fecha de inicio"
            );
        }

        System.out.println("Fecha inicio: " + loan.getStartDate());
        System.out.println("Fecha fin: " + loan.getEndDate());

        if (ChronoUnit.DAYS.between((loan.getStartDate()), loan.getEndDate()) > 14) {
            throw  new IllegalArgumentException(
                    "El préstamo no puede superar los 14 días"
            );
        }

        Long loanId = id == null ? -1L  : id;

        if (this.loanRepository.existsByGameIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndIdNot(
                dto.getGame().getId(),
                loan.getEndDate(),
                loan.getStartDate(),
                loanId)) {
            throw new IllegalArgumentException(
                    "El juego ya está prestado durante las fechas seleccionadas"
            );
        }

        if (this.loanRepository.countByClientIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndIdNot(
                dto.getClient().getId(),
                loan.getEndDate(),
                loan.getStartDate(),
                loanId) >=2) {
            throw  new IllegalArgumentException(
                    "El cliente no puede tener más de dos préstamos a la vez"
            );
        }

        this.loanRepository.save(loan);
    }

    @Override
    public void delete(Long id) {
        this.loanRepository.deleteById(id);

    }
}
