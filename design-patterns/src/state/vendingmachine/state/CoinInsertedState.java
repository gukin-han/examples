package state.vendingmachine.state;

import state.vendingmachine.context.VendingMachine;

public class CoinInsertedState implements VendingMachineState {

    private final VendingMachine vendingMachine;

    public CoinInsertedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amount) {
        vendingMachine.insertCoin(amount);
    }

    @Override
    public void selectProduct(String productId) {

    }

    @Override
    public void dispenseProduct() {

    }

    @Override
    public void returnChange() {

    }

    @Override
    public void displayStatus() {

    }
}
