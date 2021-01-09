package pl.pjatk.gameplay.Player.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.Player.model.Player;

@Service
public class DamageService {
    public DamageService() {
    }

    public Player dealCriticalDamage(Player attackerPlayer, Player defenderPlayer){
        defenderPlayer.setHP(defenderPlayer.getHP() - 2 * attackerPlayer.getAttack());
        return defenderPlayer;
    }

    public Player buffHP(Player player){
        if (player.getHP() > 10) {
            player.setHP(player.getHP() * 2);
        }
        else{
            player.setHP(player.getHP()*3);
        }
        return player;
    }

    public Player toxic(Player player){
        player.setHP(player.getHP()-2);
        return player;
    }

    public Player renewMP(Player player){
        if (player.getMP() > 10){
            player.setMP(player.getMP()+5);
        }
        else{
            player.setMP(player.getMP()+10);
        }
        return player;
    }

    public Player suicide(Player player){
        player.setHP(0);
        return player;
    }

    public Player attack(Player attacker, Player defender) {
        defender.setHP(
                defender.getHP() - attacker.getAttack()
        );
        return defender;
    }

}