package com.example.cash.machine.adapter.out;

import com.example.cash.machine.adapter.out.jpa.mapper.CashMachineMapper;
import com.example.cash.machine.adapter.out.jpa.repository.CashMachineEntityRepository;
import com.example.cash.machine.application.port.out.LoadCashMachinePort;
import com.example.cash.machine.domain.entities.CashMachine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class JpaLoadCashMachineAdapter implements LoadCashMachinePort {

    private final CashMachineMapper cashMachineMapper;
    private final CashMachineEntityRepository cashMachineEntityRepository;

    @Override
    @Transactional
    public Optional<CashMachine> getById(Long id) {
        return cashMachineEntityRepository.findById(id).map(cashMachineMapper::toDomain);
    }
}
