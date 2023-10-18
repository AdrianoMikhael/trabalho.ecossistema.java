package main.java.br.nassau.ecossistema;

import java.util.List;
import java.util.Random;

public class Animal {
    private final char abbreviation;
    private final String name;
    private final String species;
    private int life;
    private int atualX;
    private int atualY;

    public Animal(String name, String species, char abbreviation, int life, int startX, int startY) {
        this.name = name;
        this.species = species;
        this.abbreviation = abbreviation;
        this.life = life;
        this.atualX = startX;
        this.atualY = startY;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public char getAbbreviation() {
        return abbreviation;
    }

    public int getLife() {
        return life;
    }

    public int getAtualX() {
        return atualX;
    }

    public int getAtualY() {
        return atualY;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void decreaseLife() {
        this.life--;
    }

    public void increaseLife() {
        this.life++;
    }

    public void andar(Terreno terreno, List<Animal> animals) {
        int direcao = new Random().nextInt(4);
        int novoX = atualX;
        int novoY = atualY;

        switch (direcao) {
            case 0: // baixo
                novoX++;
                break;
            case 1: // cima
                novoX--;
                break;
            case 2: // direita
                novoY++;
                break;
            case 3: // esquerda
                novoY--;
                break;
        }

        if (novoX >= 0 && novoX < terreno.getTamanho() && novoY >= 0 && novoY < terreno.getTamanho()) {
            if (animals.contains(this)) {
                terreno.tirarAnimal(atualX, atualY, animals);
                atualX = novoX;
                atualY = novoY;
                terreno.adicionarAnimal(this, novoX, novoY);
            }
        }
    }

    public boolean podeAtacar(Animal outroAnimal) {
        return this.getSpecies().equals("Predador") && !this.equals(outroAnimal)
                && outroAnimal.getSpecies().equals("Presa");
    }

    public void atacar(Animal outroAnimal) {
        if (this.podeAtacar(outroAnimal)) {
            outroAnimal.decreaseLife();
        }
    }
}
