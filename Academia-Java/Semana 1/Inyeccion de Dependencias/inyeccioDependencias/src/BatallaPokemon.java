public class BatallaPokemon {
    private Pokemon poke;

    public BatallaPokemon(Pokemon poke) {
        this.poke = poke;
    }

    public void accion() {
        System.out.println(poke.getClass().getSimpleName() + " " + poke.atacar());
    }
}
