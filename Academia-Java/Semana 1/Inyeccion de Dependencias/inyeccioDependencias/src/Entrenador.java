public class Entrenador {

    public static BatallaPokemon elegir(EquipoPokemon pokemon) {
        switch (pokemon) {
            case PIKACHU:
                Pokemon pok1 = new Pikachu();
                return new BatallaPokemon(pok1);
            case CHARIZARD:
                Pokemon pok2 = new Charizard();
                return new BatallaPokemon(pok2);
            case BLASTOISE:
                Pokemon pok3 = new Blastoise();
                return new BatallaPokemon(pok3);
            case VENUSAUR:
                Pokemon pok4 = new Venusaur();
                return new BatallaPokemon(pok4);
            case LAPRAS:
                Pokemon pok5 = new Lapras();
                return new BatallaPokemon(pok5);
            case SNORLAX:
                Pokemon pok6 = new Snorlax();
                return new BatallaPokemon(pok6);
            default:
                throw new IllegalArgumentException("Ese pokemon no esta en el equipo");
        }
    }
}
