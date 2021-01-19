import java.util.Random;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
      // system objects
    Scanner in = new Scanner(System.in);
    Random rand = new Random();

    // game variables 
    String[] enemies = {"Zombie", "Skeleton", "Ghost", "Assassin"};
    int maxEnemyHealth = 75;
    int enemyAttackDamage = 25;
    int escapeRate = 50; 

    // player variables 
    int killStreak = 0; 
    int health = 100;
    int attackDamage = 50;
    int healthPots = 3;
    int healthPotHealAmount = 30; 
    int healthDropChance = 50; // percentage 

    boolean running = true; 

    // start game 
    GAME: 
        while (running) {
            System.out.println("------------------");
            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)];
            System.out.println(enemy + " appeared!");

            while (enemyHealth > 0 && health > 0 && running) {
                System.out.println(enemy + " health: " + enemyHealth);
                System.out.println("What would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Use Potion");
                System.out.println("\t3. Run!");

                String input = in.nextLine();
                switch (input) {
                    case "1": 
                        enemyHealth -= attackDamage;
                        if (enemyHealth > 0) {
                            int enemyDamage = rand.nextInt(enemyAttackDamage);
                            health -= enemyDamage;
                            System.out.println(enemy + " attacks for " + enemyDamage + "! Your health: " + health); 
                        }
                        else if (health < 0) {
                            System.out.println("You died :(");
                        }
                        else {
                            System.out.println("Congrats! You defeated the " + enemy); 
                            killStreak+=1;
                            System.out.println("Total kills: " + killStreak);
                            int potion = rand.nextInt(100); 
                            if (potion < healthDropChance) {
                                System.out.println("You received a potion! Total potions: " + healthPots);
                                healthPots+=1;
                            }
                            System.out.println("Do you want to leave the dungeon or continue fighting? y/n");
                            String quit = in.nextLine();
                            if (quit.equals("y")) {
                                System.out.println("Thanks for playing!");
                                running = false; 
                            }
                        }
                        break;
                    case "2":
                        health+= healthPotHealAmount;
                        healthPots-=1;
                    break;
                    case "3":
                        int escape = rand.nextInt(100); 
                        System.out.println("Your escape chance: " + escape);
                        if (escape < escapeRate) {
                             System.out.println("You run away");
                            enemyHealth = 0;
                        }
                        else {
                            System.out.println("Cannot escape!");
                            int enemyDamage = rand.nextInt(enemyAttackDamage);
                            health -= enemyDamage;
                            System.out.println(enemy + " attacks for " + enemyDamage + "! Your health: " + health); 
                        }
                        break;
                    default: 
                        System.out.println("Invalid option; Try again"); 
                }
            }
        }
  }
}