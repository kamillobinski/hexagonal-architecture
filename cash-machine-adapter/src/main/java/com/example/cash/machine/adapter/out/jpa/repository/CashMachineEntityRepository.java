package com.example.cash.machine.adapter.out.jpa.repository;

import com.example.cash.machine.adapter.out.jpa.model.CashMachineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashMachineEntityRepository extends JpaRepository<CashMachineEntity, Long> {
}
