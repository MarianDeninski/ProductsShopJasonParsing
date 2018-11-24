package productshop.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class UsersProductView {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private int age;

    @Expose
    private List<SoldProductsView> soldProducts;


    public UsersProductView() {

        this.soldProducts = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<SoldProductsView> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<SoldProductsView> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
