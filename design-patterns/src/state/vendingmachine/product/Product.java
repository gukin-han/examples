package state.vendingmachine.product;

public enum Product {
    COLA("A1", "콜라", 1000L),
    CIDER("A2", "사이다", 1000L),
    COFFEE("B1", "커피", 800L),
    WATER("B2", "물", 500L);


    Product(String id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    private final String id;
    private final String name;
    private final Long price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }
}
