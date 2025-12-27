public class AdminPanel {

    public void CreateDataBase(){
        System.out.println("System: Creating data . . .");
        createCities();
        createVehicles();
        createTestUser();
        System.out.println("System successfully created data!");
    }

    public void createCities(){
        AppData.cities.add(new City(EuropeCity.Istanbul , 40 , 30));
        AppData.cities.add(new City(EuropeCity.London , 150 , 80));
        AppData.cities.add(new City(EuropeCity.Moscow , 200 , 120));
        AppData.cities.add(new City(EuropeCity.Berlin , 130 , 90));
        AppData.cities.add(new City(EuropeCity.Paris , 120 , 70));
        AppData.cities.add(new City(EuropeCity.Madrid , 80 , 20));
    }

    public void createVehicles(){
        try {
            AppData.vehicles.add(new Truck("61 K 1254", 5000, 15.5, 55));
            AppData.vehicles.add(new Truck("34 IST 348", 8000, 20.0, 45));
            AppData.vehicles.add(new Motorcycle("34 AB 456", 50, 3.5, 85));
            AppData.vehicles.add(new Motorcycle("06 ANK 99", 75, 4.0, 90));
            AppData.vehicles.add(new Drone("AI-DRONE-01", 2, 5.0, 130));
            AppData.vehicles.add(new Drone("AI-DRONE-02", 5, 8.5, 120));
        }catch (WrongDataPass e){
            System.err.println("CRITICAL ERROR: Failed to create vehicles! " + e.getMessage());
        }
    }

    public void createTestUser() {
        try {
            Customer customer = new Customer(
                    "Avaz",
                    "avaz@example.com",
                    "+905551234567",
                    "123",
                    new BankAccount(Banks.Bank_Of_America),
                    null
            );
            AppData.customers.add(customer);
        }catch (WrongDataPass e){
            System.err.println("CRITICAL ERROR:Failed to create test user! " + e.getMessage());
        }
    }
}
