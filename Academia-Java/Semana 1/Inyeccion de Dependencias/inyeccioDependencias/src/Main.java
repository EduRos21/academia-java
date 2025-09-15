//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Batalla Pokemon");

        BatallaPokemon bat;
        bat = Entrenador.elegir(EquipoPokemon.PIKACHU);
        bat.accion();

        System.out.println("Pikachu no puede continuar");

        bat = Entrenador.elegir(EquipoPokemon.SNORLAX);
        bat.accion();

        System.out.println("Snorlax ya no puede seguir");

        bat = Entrenador.elegir(EquipoPokemon.LAPRAS);
        bat.accion();

        System.out.println("Lapras no puede mas");

        bat = Entrenador.elegir(EquipoPokemon.VENUSAUR);
        bat.accion();

        System.out.println("Venosaur no puede continuar");

        bat = Entrenador.elegir(EquipoPokemon.BLASTOISE);
        bat.accion();

        System.out.println("Blastoise no puede mas");

        bat = Entrenador.elegir(EquipoPokemon.CHARIZARD);
        bat.accion();

        System.out.println("Charizard no puede continuar, pierdes la batalla");
    }
}