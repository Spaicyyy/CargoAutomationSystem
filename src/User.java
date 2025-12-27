public abstract class User {
    protected String name ;
    protected String email;
    protected String phoneNumber;
    protected String password;

    public User(String name , String email , String phoneNumber , String password) throws WrongDataPass{
        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setPassword(password);
    }

    //Getter Setter
    public void setName(String name) throws WrongDataPass {
        if(name == null){
            throw new WrongDataPass("Name can't be empty!");
        }else{
            this.name=name;
        }
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) throws WrongDataPass{
        if(email== null){
            throw new WrongDataPass("Email can't be empty!");
        }else{
            this.email=email;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) throws WrongDataPass {
        if(phoneNumber == null){
            throw new WrongDataPass("Phone number can't be empty!");
        }else{
            this.phoneNumber=phoneNumber;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPassword(String password) throws WrongDataPass{
        if(password == null){
            throw new WrongDataPass("Password can't be empty!");
        }else{
            this.password=password;
        }
    }

    public String getPassword() {
        return password;
    }
}
