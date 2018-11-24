package productshop.service;

import productshop.domain.dtos.binding.UserSeedDto;
import productshop.domain.dtos.view.AllUsersView;
import productshop.domain.dtos.view.UserView;

import java.util.List;

public interface UserService {

    void seedUsers(UserSeedDto[] userSeedDtos);

    List<UserView> successfullySoldProducts();

    AllUsersView userWithSoldProducts();
}
