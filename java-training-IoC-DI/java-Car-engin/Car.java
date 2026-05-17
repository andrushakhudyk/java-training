class Car{
    private Engin engin;
    public Car(Engin engin){
        this.engin = engin;
    }

    void drive(){
        engin.start();
        System.out.println("Car is drive");
    }
}