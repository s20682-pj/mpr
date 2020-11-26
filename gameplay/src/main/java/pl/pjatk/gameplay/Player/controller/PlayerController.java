package pl.pjatk.gameplay.Player.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.gameplay.Player.model.Player;
import pl.pjatk.gameplay.Player.service.PlayerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private PlayerService playerService;

    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> findAll(){
        return ResponseEntity.ok(playerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Player>> findbyId(@PathVariable long id){
        Optional<Player> byId = playerService.findbyId(id);
        if (byId.isPresent()){
            return ResponseEntity.ok(byId);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Player> save(@RequestBody Player player){
        return ResponseEntity.ok(playerService.save(player));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> update(@PathVariable long id, @RequestBody Player playerWithUpdatedProperties){
        return ResponseEntity.ok(playerService.update(id,playerWithUpdatedProperties));
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        playerService.delete(id);

        return ResponseEntity.ok().build();
    }
}
