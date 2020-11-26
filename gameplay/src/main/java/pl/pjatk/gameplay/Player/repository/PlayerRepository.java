package pl.pjatk.gameplay.Player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.gameplay.Player.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
