package project.highthon.JoMO.domain.accumulate.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.highthon.JoMO.domain.user.domain.User;

import java.util.List;

@Repository
public interface AccumulateRepository extends JpaRepository<Accumulate, Long> {

    List<Accumulate> findAllByUser(User user);
}
