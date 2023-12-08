package user;

public class Customer {
    private  static int customerNumber = 1;
    private final int ID;
    private String fullName;
    private String email;
    private int age;
    private  String password;

    public Customer(){
        ID = customerNumber++;
        fullName = "Unknown";
        email = "Unknown";
        age = 18;
    }
    public Customer(int ID, String fullName, String email, int age, String password){
        this.ID = ID;
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.password = password;
    }
    public Customer(String fullName, String email, int age, String password){
        ID = customerNumber++;
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    public static int getCustomerNumber() {
        return customerNumber;
    }

    public static void setCustomerNumber(int customerNumber) {
        Customer.customerNumber = customerNumber;
    }

    public int getID() {
        return ID;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toFileString() {
        return  ID + ", " + fullName + ", " + email + ", " + age + ", " + password ;
    }

}
