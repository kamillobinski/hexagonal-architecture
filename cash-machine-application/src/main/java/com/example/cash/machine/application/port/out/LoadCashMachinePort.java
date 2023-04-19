package com.example.cash.machine.application.port.out;

import com.example.cash.machine.domain.entities.CashMachine;

import java.util.Optional;

public interface LoadCashMachinePort {
    Optional<CashMachine> getById(Long id);
}
