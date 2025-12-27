public class Motorcycle extends Vehicle {

    public Motorcycle(String licensePlateNumber , int maxCarryingCapacity , double costPerKilometre , int avgSpeed) throws WrongDataPass{
        super(licensePlateNumber, maxCarryingCapacity, costPerKilometre , avgSpeed);
    }

    @Override
    public double calculateCost(double distance, double cargoWeight) {
        double pricePerKmWithLoad = getCostPerKilometre() + (cargoWeight * 0.5);

        return distance * pricePerKmWithLoad;
    }

    @Override
    public void vehicleInfo(){
        System.out.println("Motorcycle , max carrying capacity:"+getMaxCarryingCapacity()+
                "\nCost per kilometre:"+getCostPerKilometre()+
                "\nAverage speed" + getAvgSpeed());
    }
}
