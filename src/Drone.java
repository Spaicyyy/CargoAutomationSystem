public class Drone extends Vehicle {

    public Drone(String licensePlateNumber , int maxCarryingCapacity , double costPerKilometre , int avgSpeed) throws WrongDataPass{
        super(licensePlateNumber, maxCarryingCapacity, costPerKilometre , avgSpeed);
    }

    @Override
    public double calculateCost(double distance, double cargoWeight) {
        double pricePerKmWithLoad = getCostPerKilometre() + (cargoWeight * 2.0);

        return distance * pricePerKmWithLoad;
    }

    @Override
    public void vehicleInfo(){
        System.out.println("Drone , max carrying capacity:"+getMaxCarryingCapacity()+
                "\nCost per kilometre:"+getCostPerKilometre()+
                "\nAverage speed" + getAvgSpeed());
    }
}
