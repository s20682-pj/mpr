package pl.pjatk.gameplay.Player.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.Player.model.Player;
import pl.pjatk.gameplay.Player.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;
    private DamageService damageService;

    public PlayerService(PlayerRepository playerRepository, DamageService damageService) {
        this.playerRepository = playerRepository;
        this.damageService = damageService;
    }

    public List<Player> findAll(){
        return playerRepository.findAll();
    }

    public Optional<Player> findbyId(long id){
        return playerRepository.findById(id);
    }

    public Player save(Player player){
        return playerRepository.save(player);
    }

    public void delete(Long id){
        playerRepository.deleteById(id);
    }

    public void deleteAll(){
        playerRepository.deleteAll();
    }

    public Player update(Long id, Player updatedPlayer) {
        updatedPlayer.setId(id);
        if (findbyId(updatedPlayer.getId()).isPresent()) {
            return playerRepository.save(updatedPlayer);
        } else {
            return null;
        }
    }

    public Player attack(Long attackerId, Long defenderId) {
        Player attacker = findbyId(attackerId).get();
        Player defender = findbyId(defenderId).get();
        defender = damageService.attack(attacker, defender);
        playerRepository.save(defender);
        return defender;
    }
}
