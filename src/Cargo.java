import java.time.LocalDateTime;

public class Cargo  implements Insured {
    //-----Alanlar ve Constructor-----
    private static final double STANDARD_INSURANCE_RATE = 0.01;
    private static final double EXPRESS_INSURANCE_RATE = 0.02;
    private static final double COLDCHAIN_INSURANCE_RATE = 0.04;

    private static final int STANDARD_SHIPPING_RATE = 0;
    private static final int EXPRESS_SHIPPING_RATE = 3;
    private static final int COLDCHAIN_SHIPPING_RATE = 4;


    protected final int id;

    protected ShippingTypes shippingType;
    protected City senderCity;
    protected City recieverCity;
    protected double cargoWeight;
    protected int moneyValue;
    protected Vehicle vehicle;

    protected static int numberOfCargo = 0;

    public Cargo(int cargoWeight, int moneyValue, City senderCity, City recieverCity, ShippingTypes cargoType, Vehicle vehicle) throws WrongDataPass {
        setCargoWeight(cargoWeight);
        setMoneyValue(moneyValue);
        setSenderCity(senderCity);
        setRecieverCity(recieverCity);
        setShippingType(cargoType);
        setVehicle(vehicle);
        numberOfCargo++;
        this.id = numberOfCargo;
    }
    //-----Alanlar ve Constructor-----


    //-----Cargonun kendi methodlari-----
    public double calculateCargoCostForEveryVehicle() {
        return vehicle.calculateCost(calculateDistance() , cargoWeight) ;
    }

    public LocalDateTime calculateEstimatedTime(){
        double arrivalTime =  calculateDistance() / vehicle.avgSpeed ;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime arrival = now.plusHours((long)arrivalTime);
        return arrival ;
    }



    public double  calculateTotalPrice(){
        return calculateCargoCostForEveryVehicle() + calculateSpecialCargoCost() ;
    }

    public double calculateSpecialCargoCost() {
        if (shippingType == ShippingTypes.StandardShipping) {
            return STANDARD_SHIPPING_RATE;
        } else if (shippingType == ShippingTypes.ExpressShipping) {
            return calculateDistance() * EXPRESS_SHIPPING_RATE;
        } else if (shippingType == ShippingTypes.ColdChainShipping) {
            return calculateDistance() * COLDCHAIN_SHIPPING_RATE;
        } else {
            return -1;
        }
    }

    public double calculateTotalCargoCost() {
        return calculateCargoCostForEveryVehicle() + calculateSpecialCargoCost();
    }

        public double calculateDistance () {
            return Math.sqrt((Math.pow((senderCity.getCordX() - recieverCity.getCordX()), 2) + Math.pow((senderCity.getCordY() - recieverCity.getCordY()), 2)));
        }

        //-----Cargonun kendi methodlari-----


        //------INSURANCE------
        @Override
        public double calculateInsurancePrice () {
            if (shippingType == ShippingTypes.StandardShipping) {
                return moneyValue * STANDARD_INSURANCE_RATE;
            } else if (shippingType == ShippingTypes.ExpressShipping) {
                return moneyValue * EXPRESS_INSURANCE_RATE;
            } else if (shippingType == ShippingTypes.ColdChainShipping) {
                return moneyValue * COLDCHAIN_INSURANCE_RATE;
            } else {
                return -1;
            }
        }

        @Override
        public void printInsurancePolicy () {
            if (shippingType == ShippingTypes.StandardShipping) {
                System.out.println("For Standard shipping riscDescription is 0.01 so your total cargo cost will be multiplied to 0.01! Your cargo's insurance price is: " + calculateInsurancePrice() + "$");
            } else if (shippingType == ShippingTypes.ExpressShipping) {
                System.out.println("For Express shipping riscDescription is 0.02 so your total cargo cost will be multiplied to 0.02! Your cargo's insurance price is: " + calculateInsurancePrice() + "$");
            } else if (shippingType == ShippingTypes.ColdChainShipping) {
                System.out.println("For Cold Chain shipping riscDescription is 0.04 so your total cargo cost will be multiplied to 0.04! Your cargo's insurance price is: " + calculateInsurancePrice() + "$");
            }
        }
        //------INSURANCE------


        //-----Getter and Setter-----
        public void setCargoWeight ( int cargoWeight) throws WrongDataPass{
            if (cargoWeight < 0) {
                throw new WrongDataPass("Cargo weight can't be negative!");
            } else {
                this.cargoWeight = cargoWeight;
            }
        }

        public double getCargoWeight () {
            return cargoWeight;
        }

        public void setMoneyValue ( int moneyValue) throws WrongDataPass{
            if (moneyValue < 0) {
                throw new WrongDataPass("Cargo's money value can't be negative!");
            } else {
                this.moneyValue = moneyValue;
            }
        }

        public int getMoneyValue () {
            return moneyValue;
        }

        public void setSenderCity (City senderCity){
            this.senderCity = senderCity;
        }

        public City getSenderCity () {
            return senderCity;
        }

        public void setRecieverCity (City recieverCity){
            this.recieverCity = recieverCity;
        }

        public City getRecieverCity () {
            return recieverCity;
        }

        public void setVehicle (Vehicle vehicle) throws WrongDataPass{
            if(vehicle.canVehicleCarry(getCargoWeight())){
                this.vehicle = vehicle;
            }else{
                throw new WrongDataPass("Selected vehicle can't carry that much!");
            }
        }

        public Vehicle getVehicle () {
            return vehicle;
        }

        public static int getNumberOfCargo () {
            return numberOfCargo;
        }

        public void setShippingType (ShippingTypes shippingType){
            this.shippingType = shippingType;
        }

        public ShippingTypes getShippingType () {
            return shippingType;
        }

        public int getId () {
            return id;
        }
        //-----Getter and Setter-----
    }
