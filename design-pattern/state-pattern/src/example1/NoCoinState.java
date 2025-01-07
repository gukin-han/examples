package example1;

public class NoCoinState implements State{

  @Override
  public void insertCoin(VendingMachine vm) {

  }

  @Override
  public void selectDrink(VendingMachine vm) {
    System.out.println("동전이 없습니다. 동전 먼저 넣어주세요.");
  }

  @Override
  public void dispenseDrink(VendingMachine vm) {
    System.out.println("동전이 없습니다. 동전 먼저 넣어주세요.");
  }

  @Override
  public void refillStock(VendingMachine vm, int count) {
    System.out.println("재고");
    vm.refillStock(count);
  }
}
