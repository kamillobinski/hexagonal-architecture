package entity;

import com.example.cash.machine.domain.commands.DepositCommand;
import com.example.cash.machine.domain.commands.WithdrawalCommand;
import com.example.cash.machine.domain.entities.CashMachine;
import com.example.cash.machine.domain.enums.EventType;
import com.example.cash.machine.domain.events.BalanceEvent;
import com.example.cash.machine.domain.events.WithdrawalDepositEvent;
import com.example.cash.machine.domain.exception.CashMachineException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CashMachineTest {

    private final long INITIAL_CASH_MACHINE_ID = 1L;
    private final BigDecimal INITIAL_CASH_MACHINE_BALANCE = new BigDecimal(1000);

    private CashMachine cashMachine;

    @BeforeEach
    void setUp() {
        cashMachine = CashMachine.builder()
                .id(INITIAL_CASH_MACHINE_ID)
                .balance(INITIAL_CASH_MACHINE_BALANCE)
                .build();
    }

    @Test
    void balance_shouldReturnBalanceEventWithValidProperties() {
        BalanceEvent event = cashMachine.balance();

        assertEquals(INITIAL_CASH_MACHINE_BALANCE, event.getCashMachine().getBalance());
    }

    @Test
    void withdraw_shouldReturnWithdrawalDepositEventWithValidProperties() {
        BigDecimal amountToWithdraw = BigDecimal.valueOf(200);
        WithdrawalCommand withdrawalCommand = WithdrawalCommand.builder().amount(amountToWithdraw).build();

        WithdrawalDepositEvent event = cashMachine.withdraw(withdrawalCommand);

        assertEquals(INITIAL_CASH_MACHINE_ID, event.getCashMachine().getId());
        assertEquals(INITIAL_CASH_MACHINE_BALANCE.subtract(amountToWithdraw), event.getCashMachine().getBalance());
        assertEquals(INITIAL_CASH_MACHINE_BALANCE, event.getBalanceBefore());
        assertEquals(INITIAL_CASH_MACHINE_BALANCE.subtract(amountToWithdraw), event.getBalanceAfter());
        assertEquals(EventType.WITHDRAWAL, event.getEventType());
        assertEquals(Instant.now().getEpochSecond(), event.getTimestamp().getEpochSecond());
    }

    @Test
    void withdraw_shouldThrowCashMachineExceptionWhenAmountIsGreaterThanBalance() {
        BigDecimal amountToWithdraw = BigDecimal.valueOf(2200);
        WithdrawalCommand withdrawalCommand = WithdrawalCommand.builder().amount(amountToWithdraw).build();

        assertThrows(CashMachineException.class, () -> {
            cashMachine.withdraw(withdrawalCommand);
        });
    }

    @Test
    void withdraw_shouldThrowCashMachineExceptionWhenAmountIsZero() {
        BigDecimal amountToWithdraw = BigDecimal.ZERO;
        WithdrawalCommand withdrawalCommand = WithdrawalCommand.builder().amount(amountToWithdraw).build();

        assertThrows(CashMachineException.class, () -> {
            cashMachine.withdraw(withdrawalCommand);
        });
    }

    @Test
    void withdraw_shouldThrowCashMachineExceptionWhenAmountIsNegative() {
        BigDecimal amountToWithdraw = BigDecimal.valueOf(-100);
        WithdrawalCommand withdrawalCommand = WithdrawalCommand.builder().amount(amountToWithdraw).build();

        assertThrows(CashMachineException.class, () -> {
            cashMachine.withdraw(withdrawalCommand);
        });
    }

    @Test
    void withdraw_shouldThrowCashMachineExceptionWhenBalanceIsZero() {
        BigDecimal amountToWithdraw = BigDecimal.valueOf(1000);
        WithdrawalCommand withdrawalCommand = WithdrawalCommand.builder().amount(amountToWithdraw).build();
        CashMachine zeroBalanceCashMachine = CashMachine.builder()
                .id(INITIAL_CASH_MACHINE_ID)
                .balance(BigDecimal.ZERO)
                .build();

        assertThrows(CashMachineException.class, () -> {
            zeroBalanceCashMachine.withdraw(withdrawalCommand);
        });
    }

    @Test
    void deposit_shouldReturnWithdrawalDepositEventWithValidProperties() {
        BigDecimal amountToDeposit = BigDecimal.valueOf(200);
        DepositCommand depositCommand = DepositCommand.builder().amount(amountToDeposit).build();

        WithdrawalDepositEvent event = cashMachine.deposit(depositCommand);

        assertEquals(INITIAL_CASH_MACHINE_ID, event.getCashMachine().getId());
        assertEquals(INITIAL_CASH_MACHINE_BALANCE.add(amountToDeposit), event.getCashMachine().getBalance());
        assertEquals(INITIAL_CASH_MACHINE_BALANCE, event.getBalanceBefore());
        assertEquals(INITIAL_CASH_MACHINE_BALANCE.add(amountToDeposit), event.getBalanceAfter());
        assertEquals(EventType.DEPOSIT, event.getEventType());
        assertEquals(Instant.now().getEpochSecond(), event.getTimestamp().getEpochSecond());
    }

    @Test
    void deposit_shouldThrowCashMachineExceptionWhenAmountIsNegative() {
        BigDecimal amountToDeposit = BigDecimal.valueOf(-100);
        DepositCommand depositCommand = DepositCommand.builder().amount(amountToDeposit).build();

        assertThrows(CashMachineException.class, () -> {
            cashMachine.deposit(depositCommand);
        });
    }

    @Test
    void deposit_shouldThrowCashMachineExceptionWhenAmountIsZero() {
        BigDecimal amountToDeposit = BigDecimal.ZERO;
        DepositCommand depositCommand = DepositCommand.builder().amount(amountToDeposit).build();

        assertThrows(CashMachineException.class, () -> {
            cashMachine.deposit(depositCommand);
        });
    }
}
