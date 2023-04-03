package com.example.cash.machine.domain.commands;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DepositCommand {

  private final int amount;
}
