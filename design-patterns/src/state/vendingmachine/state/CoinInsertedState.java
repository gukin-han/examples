package state.vendingmachine.state;

import state.vendingmachine.context.VendingMachine;

public class CoinInsertedState implements VendingMachineState {

    private final VendingMachine vendingMachine;

    public CoinInsertedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amount) {
        this.vendingMachine.addBalance(amount);
        System.out.println("[CoinInsertedState -> CoinInsertedState] 동전이 투입되었습니다");
    }

    @Override
    public void selectProduct(String productId) {
        try {
            String selectedProductName = this.vendingMachine.selectProduct(productId);
            this.vendingMachine.changeToSelectedState();
            System.out.println("[CoinInsertedState -> ProductSelectedState] 상품이 선택되었습니다 : " + selectedProductName);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dispenseProduct() {
        if (this.vendingMachine.isAffordable()) {
            String productName = this.vendingMachine.checkOut();
            System.out.println("[]");
        }
    }

    @Override
    public void returnChange() {

    }

    @Override
    public void displayStatus() {

    }
}
