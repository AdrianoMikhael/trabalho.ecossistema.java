package main.java.br.nassau.ecossistema;

import java.util.List;

public class Terreno {
    private char[][] mapa;
    private int tamanho;

    public Terreno(int tamanho) {
        this.tamanho = tamanho;
        mapa = new char[tamanho][tamanho];
        inicializarMapa();
    }

    public int getTamanho() {
        return tamanho;
    }

    private void inicializarMapa() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                mapa[i][j] = ' ';
            }
        }
    }

    public void adicionarAnimal(Animal animal, int x, int y) {
        if (x >= 0 && x < tamanho && y >= 0 && y < tamanho) {
            mapa[x][y] = animal.getAbbreviation();
        }
    }

    public void adicionarPlanta(char abbreviation, int x, int y) {
        if (x >= 0 && x < tamanho && y >= 0 && y < tamanho) {
            mapa[x][y] = abbreviation;
        }
    }

    public void tirarAnimal(int xAtual, int yAtual, List<Animal> animals) {
        if (xAtual >= 0 && xAtual < tamanho && yAtual >= 0 && yAtual < tamanho) {
            mapa[xAtual][yAtual] = ' ';
        }
    }

    public void exibirTerreno(List<Animal> animals, List<Planta> plantas) {
        limparTerreno();

        for (Planta planta : plantas) {
            adicionarPlanta(planta.getAbbreviation(), planta.getAtualX(), planta.getAtualY());
        }

        for (Animal animal : animals) {
            if (animal.getLife() > 0) {
                adicionarAnimal(animal, animal.getAtualX(), animal.getAtualY());
            }
        }

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void limparTerreno() {
        inicializarMapa();
    }
}
