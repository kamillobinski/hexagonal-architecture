package com.example.cash.machine.adapter.out;

import com.example.cash.machine.adapter.out.jpa.mapper.CashMachineMapper;
import com.example.cash.machine.adapter.out.jpa.repository.CashMachineEntityRepository;
import com.example.cash.machine.application.port.out.SaveCashMachinePort;
import com.example.cash.machine.domain.entities.CashMachine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class JpaSaveCashMachineAdapter implements SaveCashMachinePort {
    private final CashMachineEntityRepository cashMachineEntityRepository;
    private final CashMachineMapper cashMachineMapper;

    @Override
    @Transactional
    public void save(CashMachine cashMachine) {
        cashMachineEntityRepository.save(cashMachineMapper.toEntity(cashMachine));
    }
}
