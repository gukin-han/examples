package state.vendingmachine.state;

public interface VendingMachineState {
    void insertCoin(int amount);

    void selectProduct(String productId);

    void dispenseProduct();

    void returnChange();

    void displayStatus();
}
