import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

abstract class Player {
    protected String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int makeGuess();
}

class ComputerPlayer extends Player {
    public ComputerPlayer() {
        super("Komputer");
    }

    public int makeGuess() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }
}

class HumanPlayer extends Player {
    private Scanner scanner;
    private ArrayList<Integer> guesses;

    public HumanPlayer(String name, Scanner scanner) {
        super(name);
        this.scanner = scanner;
        this.guesses = new ArrayList<>();
    }

    public int makeGuess() {
        System.out.print(getName() + ", masukkan tebakan Anda: ");
        int guess = scanner.nextInt();
        guesses.add(guess);
        return guess;
    }

    public void displayGuesses() {
        System.out.println(getName() + "'s Tebakan: " + guesses);
    }
}

public class GameTebakAngka {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Selamat Datang di Game Tebak Angka ===");

        // Membuat objek pemain manusia
        System.out.print("Masukkan nama Anda: ");
        String playerName = scanner.nextLine();
        HumanPlayer humanPlayer = new HumanPlayer(playerName, scanner);

        // Membuat objek pemain komputer
        ComputerPlayer computerPlayer = new ComputerPlayer();

        // Mulai permainan
        boolean gameIsRunning = true;

        while (gameIsRunning) {
            int targetNumber = new Random().nextInt(100) + 1;
            System.out.println("\nSaya telah memilih angka antara 1 dan 100. Cobalah menebak!");

            // Permainan satu putaran
            playRound(humanPlayer, targetNumber);    
            playRound(computerPlayer, targetNumber);  


            // Menampilkan tebakan pemain manusia setelah setiap putaran
            humanPlayer.displayGuesses();

            // Menanyakan apakah pemain ingin bermain lagi
            System.out.print("\nApakah Anda ingin bermain lagi? (ya/tidak): ");
            String playAgain = scanner.next();

            if (!playAgain.equalsIgnoreCase("ya")) {
                gameIsRunning = false;
            }
        }

        System.out.println("Terima kasih telah bermain!");

        scanner.close();
    }

    private static void playRound(Player player, int targetNumber) {
        int guess = player.makeGuess();
        System.out.println(player.getName() + " menebak: " + guess);

        if (guess == targetNumber) {
            System.out.println(player.getName() + " menebak dengan benar!");
        } else {
            System.out.println(player.getName() + " gagal menebak. Angka yang benar adalah: " + targetNumber);
        }
    }
}
