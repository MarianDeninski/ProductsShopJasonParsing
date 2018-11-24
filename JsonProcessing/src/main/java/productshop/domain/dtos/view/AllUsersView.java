package productshop.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class AllUsersView {

    @Expose
    private int usersCount;

    @Expose
    private List<UsersProductView> usersProductViews;

    public AllUsersView() {

        this.usersProductViews = new ArrayList<>();
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public List<UsersProductView> getUsersProductViews() {
        return usersProductViews;
    }

    public void setUsersProductViews(List<UsersProductView> usersProductViews) {
        this.usersProductViews = usersProductViews;
    }
}
