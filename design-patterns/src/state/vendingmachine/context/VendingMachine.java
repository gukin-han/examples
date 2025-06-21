package state.vendingmachine.context;

import state.vendingmachine.product.Product;
import state.vendingmachine.state.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class VendingMachine {

    private final VendingMachineState coinInsertedState;
    private final VendingMachineState idleState;
    private final VendingMachineState productSelectedState;
    private final VendingMachineState dispensingState;

    private Map<String, Product> products = Arrays.stream(Product.values())
            .collect(Collectors.toMap(Product::getName, p -> p));
    private String selectedProduct = null;
    private VendingMachineState currentState;
    private int currentBalance = 0;

    public VendingMachine() {
        this.coinInsertedState = new CoinInsertedState(this);
        this.idleState = new IdleState(this);
        this.productSelectedState = new ProductSelectedState(this);
        this.dispensingState = new DispensingState(this);
        this.currentState = this.idleState;

    }

    public VendingMachineState getCoinInsertedState() {
        return coinInsertedState;
    }

    public VendingMachineState getIdleState() {
        return idleState;
    }

    public VendingMachineState getProductSelectedState() {
        return productSelectedState;
    }

    public VendingMachineState getDispensingState() {
        return dispensingState;
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }

    public String getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(String selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public VendingMachineState getCurrentState() {
        return currentState;
    }

    public void insertCoin(int amount) {
        this.currentBalance = this.currentBalance + amount;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }
}
