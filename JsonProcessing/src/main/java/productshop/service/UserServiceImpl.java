package productshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productshop.domain.dtos.binding.UserSeedDto;
import productshop.domain.dtos.view.*;
import productshop.domain.entities.Product;
import productshop.domain.entities.User;
import productshop.repository.UserRepository;
import productshop.util.ValidatorUtil;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedUsers(UserSeedDto[] userSeedDtos) {
        for (UserSeedDto userSeedDto : userSeedDtos) {
            if (!this.validatorUtil.isValid(userSeedDto)) {
                this.validatorUtil.violations(userSeedDto)
                        .forEach(violation -> System.out.println(violation.getMessage()));

                continue;
            }

            User entity = this.modelMapper.map(userSeedDto, User.class);

            this.userRepository.saveAndFlush(entity);
        }
    }

    @Override
    public List<UserView> successfullySoldProducts() {

        List<User> userrs = this.userRepository.findAllUsersWithSoldProducts();

        List<UserView> userViews = new ArrayList<>();

        for (User user : userrs) {

            UserView userView = new UserView();

            userView.setFirstName(user.getFirstName());
            userView.setLastName(user.getLastName());
            for (Product product : user.getSoldProducts()) {

                ProductView productView = new ProductView();

                if(product.getBuyer()==null){
                    continue;
                }
                productView.setBuyerFirstName(product.getBuyer().getFirstName());
                productView.setBuyerLastName(product.getBuyer().getLastName());
                productView.setName(product.getName());
                productView.setPrice(product.getPrice());
                userView.getProductViews().add(productView);
            }
            userViews.add(userView);
        }

        return userViews;
    }

    @Override
    public AllUsersView userWithSoldProducts() {

        List<User> users = this.userRepository.findAllUsersWithSoldProductsOrdered();


        AllUsersView allUsersView = new AllUsersView();
        allUsersView.setUsersCount(users.size());
        for (User user : users) {
            UsersProductView usersProductView = new UsersProductView();
            usersProductView.setFirstName(user.getFirstName());
            usersProductView.setLastName(user.getLastName());
            usersProductView.setAge(user.getAge());

            SoldProductsView soldProductsView = new SoldProductsView();
            soldProductsView.setCount(user.getSoldProducts().size());



            for (Product product : user.getSoldProducts()) {
                ProductsView productsView = new ProductsView();

                productsView.setName(product.getName());
                productsView.setPrice(product.getPrice());
                soldProductsView.getProducts().add(productsView);
            }

            usersProductView.getSoldProducts().add(soldProductsView);
            allUsersView.getUsersProductViews().add(usersProductView);

        }
        return allUsersView;
    }
}
