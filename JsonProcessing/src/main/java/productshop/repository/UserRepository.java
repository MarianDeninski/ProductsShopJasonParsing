package productshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import productshop.domain.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User AS u " +
            "JOIN Product AS p ON p.seller = u.id " +
            "WHERE p.buyer IS NOT NULL " +
            "GROUP BY u.id")
    List<User> findAllUsersWithSoldProducts();

    @Query("SELECT u FROM User AS u " +
            "JOIN Product AS p ON p.seller = u.id " +
            "WHERE p.buyer IS NOT NULL " +
            "GROUP BY u.id " +
            "ORDER BY u.soldProducts.size DESC, u.lastName")
    List<User> findAllUsersWithSoldProductsOrdered();
}
