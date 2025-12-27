public class Truck extends Vehicle {

    public Truck(String licensePlateNumber , int maxCarryingCapacity , double costPerKilometre , int avgSpeed) throws WrongDataPass{
        super(licensePlateNumber, maxCarryingCapacity, costPerKilometre , avgSpeed);
    }

    @Override
    public double calculateCost(double distance, double cargoWeight) {
        double distancePrice = distance * getCostPerKilometre();
        double weightPrice = cargoWeight * 0.2;

        return distancePrice + weightPrice;
    }

    @Override
    public void vehicleInfo(){
        System.out.println("Truck , max carrying capacity:"+getMaxCarryingCapacity()+
                            "\nCost per kilometre:"+getCostPerKilometre()+
                            "\nAverage speed" + getAvgSpeed());
    }
}
