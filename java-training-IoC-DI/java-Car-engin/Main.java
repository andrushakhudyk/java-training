public class Main{
    public static void main(String[] args){
        Car car = new Car(new ElectricEngin());
        Car car1 = new Car(new PetrolEngin());
        Car car2 = new Car(new NuckEngin());
        car.drive();
        car1.drive();
        car2.drive();
    }
}