package main.java.br.nassau.ecossistema;

public class Semente extends Planta {
    public Semente(int atualX, int atualY) {
       
        super('|', "Semente", atualX, atualY);
    }

    // Método para alimentar um animal
    public void alimentarAnimal(Animal animal) {
        System.out.printf("%c recebeu +1 de vida por ter se alimentado %n", animal.getAbbreviation());
        animal.increaseLife();
    }
}

