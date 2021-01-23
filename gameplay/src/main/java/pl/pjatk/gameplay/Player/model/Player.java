package pl.pjatk.gameplay.Player.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int HP;
    private int MP;
    private String name;
    private int attack;
    @OneToMany(mappedBy = "player",
                cascade = CascadeType.ALL)
    private List<Message> messageList = new ArrayList<>();

    public Player() {
    }

    public Player(Long id, String name, int HP, int MP, int attack, List<Message> messageList) {
        this.id = id;
        this.name = name;
        this.HP = HP;
        this.MP = MP;
        this.attack = attack;
        this.messageList = messageList;
    }

    public Player(String name, int HP, int MP, int attack) {
        this.name = name;
        this.HP = HP;
        this.MP = MP;
        this.attack = attack;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public int getHP() {

        return HP;
    }

    public void setHP(int HP) {

        this.HP = HP;
    }

    public int getMP() {

        return MP;
    }

    public void setMP(int MP) {

        this.MP = MP;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getAttack() {

        return attack;
    }

    public void setAttack(int attack) {

        this.attack = attack;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
