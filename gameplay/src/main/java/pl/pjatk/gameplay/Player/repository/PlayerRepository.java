package pl.pjatk.gameplay.Player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pjatk.gameplay.Player.model.Player;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p where p.HP = :HP")
    List<Player> getPlayersWhereHpIsFull(int HP);

    List<Player> findByHPAndName(int HP, String name);
}
