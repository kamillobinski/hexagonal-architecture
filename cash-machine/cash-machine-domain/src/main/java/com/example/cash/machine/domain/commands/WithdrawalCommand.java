package com.example.cash.machine.domain.commands;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WithdrawalCommand {

  private final int amount;
}
