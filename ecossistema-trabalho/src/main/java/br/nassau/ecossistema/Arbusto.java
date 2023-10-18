package main.java.br.nassau.ecossistema;

public class Arbusto extends Planta {

    public Arbusto(int atualX, int atualY) {
        
        super('*', "Arbusto", atualX, atualY);
    }

    // Método para permitir que um animal se esconda no arbusto
    public void esconderAnimal(Animal animal) {
        System.out.printf("%c está escondido %n", animal.getAbbreviation());
    }
}
