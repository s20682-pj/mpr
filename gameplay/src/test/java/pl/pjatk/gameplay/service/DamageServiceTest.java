package pl.pjatk.gameplay.service;

import org.junit.jupiter.api.Test;
import pl.pjatk.gameplay.Player.model.Player;
import pl.pjatk.gameplay.Player.service.DamageService;

import static org.assertj.core.api.Assertions.assertThat;

public class DamageServiceTest {
    private DamageService damageService = new DamageService();

    @Test
    public void playerShouldDealCriticalDamage(){
        //given
        Player attackerPlayer = new Player("A",20,10,10);
        Player defenderPlayer = new Player("B",30,15,8);
        //when
        defenderPlayer = damageService.dealCriticalDamage(attackerPlayer,defenderPlayer);
        //then
        assertThat(defenderPlayer.getHP()).isEqualTo(10);
    }

    @Test
    public void playerHPShouldIncrease(){
        //given
        Player player1 = new Player("A",20,10,10);
        Player player2 = new Player("B",7,15,8);
        //when
        player1 = damageService.buffHP(player1);
        player2 = damageService.buffHP(player2);
        //then
        assertThat(player1.getHP()).isEqualTo(40);
        assertThat(player2.getHP()).isEqualTo(21);
    }

    @Test
    public void playerIsPoisonedAndHisHPShouldDecrase(){
        //given
        Player player = new Player("A",20,10,10);
        //when
        player = damageService.toxic(player);
        //then
        assertThat(player.getHP()).isEqualTo(18);
    }

    @Test
    public void playerMPShouldIncrease(){
        //given
        Player player1 = new Player("A",20,15,10);
        Player player2 = new Player("B",7,5,8);
        //when
        player1 = damageService.renewMP(player1);
        player2 = damageService.renewMP(player2);
        //then
        assertThat(player1.getMP()).isEqualTo(20);
        assertThat(player2.getMP()).isEqualTo(15);
    }

    @Test
    public void playerHPShouldGoToZero(){
        //given
        Player player = new Player("A",20,10,10);
        //when
        player = damageService.suicide(player);
        //then
        assertThat(player.getHP()).isEqualTo(0);
    }
}
