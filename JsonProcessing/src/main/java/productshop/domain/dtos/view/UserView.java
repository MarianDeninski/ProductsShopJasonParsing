package productshop.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class UserView {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private List<ProductView> productViews;

    public UserView() {

        this.productViews = new ArrayList<>();
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

    public List<ProductView> getProductViews() {
        return productViews;
    }

    public void setProductViews(List<ProductView> productViews) {
        this.productViews = productViews;
    }
}
