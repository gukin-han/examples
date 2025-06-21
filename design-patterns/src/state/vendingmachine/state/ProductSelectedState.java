package state.vendingmachine.state;

import state.vendingmachine.context.VendingMachine;

public class ProductSelectedState implements VendingMachineState {

    private final VendingMachine vendingMachine;

    public ProductSelectedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amount) {
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
