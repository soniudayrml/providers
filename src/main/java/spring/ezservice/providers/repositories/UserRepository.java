package spring.ezservice.providers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.ezservice.providers.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

//    @Override
//    Optional<User> findById(Long aLong);

    Optional<User> findByUserName(String userName);
}
