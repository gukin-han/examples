package example1;

public interface State {
  void insertCoin(VendingMachine vm);

  void selectDrink(VendingMachine vm);

  void dispenseDrink(VendingMachine vm);

  void refillStock(VendingMachine vm, int count);
}
