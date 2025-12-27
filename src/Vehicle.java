public abstract class Vehicle{
    protected String licensePlateNumber;
    protected int maxCarryingCapacity;
    protected int avgSpeed;
    protected double costPerKilometre;

    protected static int numberOfVehicles = 0 ;

    public Vehicle(String licensePlateNumber , int maxCarryingCapacity , double costPerKilometre , int avgSpeed) throws WrongDataPass {
        setLicensePlateNumber(licensePlateNumber);
        setMaxCarryingCapacity(maxCarryingCapacity);
        setAvgSpeed(avgSpeed);
        setCostPerKilometre(costPerKilometre);
        numberOfVehicles++;
    }

    public abstract double calculateCost(double distance, double cargoWeight);

    public boolean canVehicleCarry(double cargoWeightKg){
        if(cargoWeightKg > getMaxCarryingCapacity()){
            System.out.println("Cargo's weight is more heaver than vehicle's max cargo weight capacity!");
            return false;
        }else{
            return true;
        }
    }

    public abstract void vehicleInfo();

    //<-----Getter and Setter----->
    public void setLicensePlateNumber(String licensePlateNumber) throws WrongDataPass {
        if(licensePlateNumber == null ){
            throw new WrongDataPass("Licence plate can't be empty!") ;
        }else{
            this.licensePlateNumber = licensePlateNumber;
        }
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setMaxCarryingCapacity(int maxCarryingCapacity) throws WrongDataPass{
        if(maxCarryingCapacity < 0){
            throw new WrongDataPass("Max carring capacity can't be negative!");
        }else{
            this.maxCarryingCapacity = maxCarryingCapacity;
        }
    }

    public int getMaxCarryingCapacity() {
        return maxCarryingCapacity;
    }

    public void setCostPerKilometre(double costPerKilometre) throws WrongDataPass {
        if(costPerKilometre < 0){
            throw new WrongDataPass("Cost per kilometre can't be negative!");
        }else{
            this.costPerKilometre = costPerKilometre;
        }
    }

    public double getCostPerKilometre() {
        return costPerKilometre;
    }

    public static int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setAvgSpeed(int avgSpeed) throws WrongDataPass{
        if(avgSpeed < 0){
            throw new WrongDataPass("Average speed can't be negative!");
        }else{
            this.avgSpeed = avgSpeed;
        }
    }

    public int getAvgSpeed() {
        return avgSpeed;
    }
}