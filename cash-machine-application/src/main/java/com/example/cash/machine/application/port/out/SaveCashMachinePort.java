package com.example.cash.machine.application.port.out;

import com.example.cash.machine.domain.entities.CashMachine;

public interface SaveCashMachinePort {
    void save(CashMachine cashMachine);
}
