package pl.pjatk.gameplay.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pjatk.gameplay.Player.model.Player;
import pl.pjatk.gameplay.Player.service.PlayerService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
public class PlayerServiceTestIT {

    @Autowired
    private PlayerService playerService;


    @BeforeEach
    void beforeEach() {
        playerService.deleteAll();
    }

    @Test
    void shouldBeEmpty(){
       List<Player> all = playerService.findAll();
       assertThat(all).isEmpty();
    }

    @Test
    void shouldAddPlayerToDB(){
        Player player = new Player("stefan",50,20,9);
        Player save = playerService.save(player);
        assertThat(save.getId()).isNotNull();
    }

    @Test
    void shouldAttack(){
        Player attacker = new Player("a",50,20,9);
        Player defender = new Player("d",40,10,11);
        Player saveA = playerService.save(attacker);
        Player saveD = playerService.save(defender);
        defender = playerService.attack(saveA.getId(),saveD.getId());
        assertThat(playerService.findbyId(saveD.getId()).get().getHP()).isEqualTo(31);
    }

    @Test
    void shouldFindById(){
        Player player = new Player("stefan",50,20,9);
        Player save = playerService.save(player);
        Optional<Player> present = playerService.findbyId(save.getId());
        assertThat(present.isPresent()).isTrue();
    }

    @Test
    void shouldFindAll(){
        Player player = new Player("stefan",50,20,9);
        Player save = playerService.save(player);
        List<Player> all = playerService.findAll();
        assertThat(all).isNotEmpty();
    }


    @Test
    void playerListShouldNotBeEmpty(){
        List<Player> playerList = playerService.findAll();
        Player player = new Player("stefan",50,20,9);
        playerList.add(player);
        assertThat(playerList).isNotEmpty();
    }

    @Test
    void shouldThrowException(){
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> playerService.findbyId(10L));
    }
}
