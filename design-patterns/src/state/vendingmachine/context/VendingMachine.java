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

    private static Map<String, Product> products = Arrays.stream(Product.values())
            .collect(Collectors.toMap(Product::getId, p -> p));

    private Product selectedProduct = null;
    private VendingMachineState currentState;
    private int currentBalance = 0;

    public VendingMachine() {
        this.coinInsertedState = new CoinInsertedState(this);
        this.idleState = new IdleState(this);
        this.productSelectedState = new ProductSelectedState(this);
        this.dispensingState = new DispensingState(this);
        this.currentState = this.idleState;

    }

    public void addBalance(int amount) {
        this.currentBalance += amount;
    }

    public String selectProduct(String productId) {
        Product selectedProduct = products.get(productId);
        if (selectedProduct == null) {
            throw new IllegalArgumentException("해당 상품은 존재하지 않습니다");
        }
        this.selectedProduct = selectedProduct;

        return this.selectedProduct.getName();
    }

    public void changeToSelectedState() {
        this.currentState = productSelectedState;
    }

    public boolean isAffordable() {
        Long price = this.selectedProduct.getPrice();
        return price <= currentBalance;
    }

    public String checkOut() {
        this.currentBalance -= this.selectedProduct.getPrice();
        String productName = this.selectedProduct.getName();
        this.selectedProduct = null;
        return productName;

    }
}
