package pl.pjatk.gameplay.Player.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Player {
    @Id
    @GeneratedValue
    private Long id;
    private int HP;
    private int MP;
    private String name;
    private int attack;

    public Player() {
    }

    public Player(Long id, String name, int HP, int MP, int attack) {
        this.id = id;
        this.name = name;
        this.HP = HP;
        this.MP = MP;
        this.attack = attack;
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
}
