public class City {
    private EuropeCity city;
    private int cordX;
    private int cordY;

    public City(EuropeCity city ,int cordX , int cordY){
        setCity(city);
        setCordX(cordX);
        setCordY(cordY);
    }

    //Getter Setter

    public void setCity(EuropeCity city) {
        this.city = city;
    }

    public EuropeCity getCity() {
        return city;
    }

    public void setCordX(int cordX) {
        this.cordX = cordX;
    }

    public int getCordX() {
        return cordX;
    }

    public void setCordY(int cordY) {
        this.cordY = cordY;
    }

    public int getCordY() {
        return cordY;
    }
}
