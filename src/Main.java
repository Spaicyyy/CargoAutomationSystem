public class Main {
    public static void main(String[] args) throws WrongDataPass {
        AdminPanel adminPanel = new AdminPanel();
        adminPanel.CreateDataBase();

        MenuController menu = new MenuController();
        menu.start();
    }



}
