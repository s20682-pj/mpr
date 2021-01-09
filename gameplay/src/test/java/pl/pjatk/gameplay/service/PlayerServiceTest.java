package pl.pjatk.gameplay.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pjatk.gameplay.Player.model.Player;
import pl.pjatk.gameplay.Player.repository.PlayerRepository;
import pl.pjatk.gameplay.Player.service.PlayerService;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {
    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @BeforeAll
    static void beforeAll(){
        System.out.println("Before all");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("Before each");
        playerRepository.save(new Player());
    }

    @AfterEach
    void afterEach(){
        System.out.println("After each");
        playerRepository.deleteAll();
    }

    @Test
    void findAllplayers() {
        //given
        when(playerRepository.findAll()).thenReturn(List.of(new Player()));
        //when
        List<Player> all = playerService.findAll();
        //then
        assertThat(all).isNotEmpty();
    }

    @Test
    void findPlayerByID(){
        //given
        when(playerRepository.findById(1L)).thenReturn(Optional.of(new Player()));
        //when
        Optional<Player> player = playerService.findbyId(1);
        //then
        assertThat(player).isNotEmpty();
    }
    
    @Test
    void saveNewPlayer(){
        //given
        Player player1 = new Player("A",20,10,10);
        Player player2 = new Player(1L,"A",20,10,10);
        when(playerRepository.save(player1)).thenReturn(player2);
        //when
        Player newPlayer = playerService.save(player1);
        //then
        assertThat(newPlayer.getId()).isEqualTo(player2.getId());
    }
    
    @Test
    void shouldDelete(){
        //Given

        //When
        playerService.delete(1L);
        //Then
        verify(playerRepository, times(1)).deleteById(1L);
    }
}