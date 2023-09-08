import java.util.*;
class Pokemon{
	
	String pokemonName;
	String pokemonType;
	int pokemonlvl = 1;
	String pokemonRarity;
	
	Pokemon(String pokemonName,String pokemonType,int level,String pokemonRarity){
		this.pokemonName = pokemonName;
		this.pokemonType = pokemonType;
		this.pokemonlvl = level;
		this.pokemonRarity = pokemonRarity;
	}
	@Override
    public String toString() {
        return "Pokemon Name: " + pokemonName +
               ", Type: " + pokemonType +
               ", Level: " + pokemonlvl +
               ", Rarity: " + pokemonRarity;
    }
	
	public void lvlPokemon() {
			pokemonlvl++;
	}
	
}

class Trainer{
	
	String trainerName;
	int food = 50;
    int pokeballs = 10;
	Trainer(String trainerName){
		this.trainerName = trainerName;
	}
	  public void catchPokemon() {
	        if (pokeballs > 0) { // Ensure you have enough pokeballs
	            pokeballs--; // Decrease the number of pokeballs
	            System.out.println("You caught a Pokemon!");
	        } else {
	            System.out.println("You don't have enough pokeballs.");
	        }
	    }

	public void viewPokedex(String trainerName, List<Pokemon> pokemons) {
		System.out.println("------------Your pokedex----------");
		System.out.println("Trainer "+ trainerName);
		System.out.println("Number of food: " + food);
		System.out.println("Number of pokeballs: "+ pokeballs);
		System.out.println();
		System.out.println("Your pokemons: ");
        for (Pokemon pokemon : pokemons) {
            System.out.println(pokemon);
        }
        System.out.println("---------------------------------");
		
	}
	
	
}
public class PokemonGame {

	public static void main(String[] args) {
		Random rand = new Random();
		Trainer trainer;
		Pokemon charmander = new Pokemon("Charmander", "Fire", 1,"uncommon");
		Pokemon bulbasaur = new Pokemon("Bulbasaur", "Grass", 1, "uncommon");
		Pokemon squirtle = new Pokemon("Squirtle", "Water",1,"uncommon");
		Pokemon pikachu = new Pokemon("Pikachu", "electric",1,"rare");
		Pokemon diglett = new Pokemon("Diglett","ground", 1,"common");
		
		List <Pokemon> pokes = new ArrayList<>();	
		Pokemon poke[] = {charmander,bulbasaur,squirtle,pikachu,diglett};
		System.out.println("Welcome to the pokemon world!");
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("\nWhat is your name trainer? ");
		String name = scan.nextLine();
		trainer = new Trainer(name);
		System.out.println("\nWelcome to the pokemon world "+ name);
		
		int choice;
		do {
		System.out.println("-------Menu-------");
		System.out.println("[1]Open pokedex");
		System.out.println("[2]Catch Pokemon");
		System.out.println("[3]Level up pokemon");
		System.out.println("[4] EXIT");
		
		choice = scan.nextInt();
		
		if(choice == 1)
			trainer.viewPokedex(name,pokes);
		if(choice == 2) {
			int randomnum = rand.nextInt(5); 
			System.out.println( name + " found a " +poke[randomnum].pokemonName);
			pokes.add(poke[randomnum]);
			trainer.catchPokemon();
					
		}
		if (choice == 3) {
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Which pokemon you want to level up: ");
			String pklvl = sc.nextLine();
			int i = 0;
			for(;i < pokes.size(); i++) {
				if(pklvl.equals(pokes.get(i).pokemonName)) {
					pokes.get(i).lvlPokemon();
					System.out.println("Your pokemon leveled up!!!");
					break;
				}
			}
		}
		
		}while(choice != 4);
		
		System.out.println("Goodbye trainer");
	}

}
