package main.java.br.nassau.ecossistema;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AcoesEcossistema {
    private List<Animal> animals = new ArrayList<>();
    private List<Planta> plantas = new ArrayList<>();
    private Terreno terreno;
    private Scanner sc = new Scanner(System.in);

    public AcoesEcossistema() {
        iniciarTerreno();
        iniciarAnimais();
        iniciarPlantas();
        System.out.println("Iniciando Ecossistema...");
    }

    private void iniciarTerreno() {
        terreno = new Terreno(10);
    }

    private void iniciarAnimais() {
        adicionarAnimais("Puma(P)", Puma.class);
        adicionarAnimais("Gato Selvagem(G)", Gatoselvagem.class);
        adicionarAnimais("Coelhos(C)", Coelho.class);
        adicionarAnimais("Rato(R)", Rato.class);
        adicionarAnimais("Zebra(Z)", Zebra.class);
    }

    private void adicionarAnimais(String tipo, Class<? extends Animal> classe) {
        System.out.printf("Digite quantos %s você quer que tenha no terreno: ", tipo);
        int quantidade = sc.nextInt();
        for (int i = 0; i < quantidade; i++) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            animals.add(criarAnimal(classe, x, y));
        }
    }

    private void iniciarPlantas() {
        adicionarPlantas("arbustos(*)", Arbusto.class);
        adicionarPlantas("Semente(I)", Semente.class);
    }

    private void adicionarPlantas(String tipo, Class<? extends Planta> classe) {
        System.out.printf("Digite quantos %s você quer que tenha no terreno: ", tipo);
        int quantidade = sc.nextInt();
        for (int i = 0; i < quantidade; i++) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            plantas.add(criarPlanta(classe, x, y));
        }
    }

    private Animal criarAnimal(Class<? extends Animal> classe, int x, int y) {
        try {
            return classe.getConstructor(int.class, int.class).newInstance(x, y);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Planta criarPlanta(Class<? extends Planta> classe, int x, int y) {
        try {
            return classe.getConstructor(int.class, int.class).newInstance(x, y);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void iniciarEcossistema() {
        while (true) {
            for (Animal animal : animals) {
                animal.andar(terreno, animals);
            }

            for (Planta planta : plantas) {
                for (Animal animal : animals) {
                    if (animal.getSpecies().equals("Presa") && planta.getType().equals("Arbusto")) {
                        if (animal.getAtualX() == planta.getAtualX() && animal.getAtualY() == planta.getAtualY())
                            ((Arbusto) planta).esconderAnimal(animal);
                    }
                }
            }

            for (Planta planta : plantas) {
                for (Animal animal : animals) {
                    if (animal.getSpecies().equals("Presa") && planta.getType().equals("Arvore")) {
                        if (animal.getAtualX() == planta.getAtualX() && animal.getAtualY() == planta.getAtualY())
                            ((Semente) planta).alimentarAnimal(animal);
                    }
                }
            }

            for (Animal predador : animals) {
                if (predador.getSpecies().equals("Predador")) {
                    for (Animal presa : animals) {
                        if (presa.getSpecies().equals("Presa") && predador.podeAtacar(presa)) {
                            if (presa.getSpecies().equals("Presa") && presa.getLife() > 0
                                    && predador.getAtualX() == presa.getAtualX()
                                    && predador.getAtualY() == presa.getAtualY() && predador.podeAtacar(presa)) {
                                predador.atacar(presa);
                                System.out.printf("%s atacou o %s%n", predador.getName(), presa.getName());
                            }
                        }
                    }
                }
            }

            List<Animal> deadAnimals = new ArrayList<>();

            for (Animal animal : animals) {
                if (animal.getLife() == 0) {
                    deadAnimals.add(animal);
                }
            }
            animals.removeAll(deadAnimals);

            terreno.exibirTerreno(animals, plantas);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
