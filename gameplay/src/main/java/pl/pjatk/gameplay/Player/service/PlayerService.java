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

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findAll(){
        return playerRepository.findAll();
    }

    public Optional<Player> findbyId(long id){
        List<Player> players = new ArrayList<>();
//        players.add(new Player((long) 1,"Player",20,15,10));
//       players.add(new Player((long) 2,"Player 2",20,15,10));

 //       players.forEach(player -> player.setMP(10));

 //       return players.stream().filter(player -> player.getId() == id).findFirst();

        return playerRepository.findById(id);
    }

    public Player save(Player player){
        return playerRepository.save(player);
    }

    public void delete(Long id){
        playerRepository.deleteById(id);
    }

    public Player update(Long id, Player updatedPlayer) {
        updatedPlayer.setId(id);
        if (findbyId(updatedPlayer.getId()).isPresent()) {
            return playerRepository.save(updatedPlayer);
        } else {
            return null;
        }
    }

}
