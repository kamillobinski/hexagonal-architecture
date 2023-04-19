package com.example.cash.machine.adapter.out.jpa.mapper;

import com.example.cash.machine.adapter.out.jpa.model.CashMachineEntity;
import com.example.cash.machine.domain.entities.CashMachine;

public class CashMachineMapper {

    public CashMachine toDomain(CashMachineEntity entity) {
        return CashMachine.builder().id(entity.getId()).balance(entity.getBalance()).build();
    }

    public CashMachineEntity toEntity(CashMachine domain) {
        return CashMachineEntity.builder().id(domain.getId()).balance(domain.getBalance()).build();
    }
}

