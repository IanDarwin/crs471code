import java.time.LocalDate;

public class Ex41Driver {
    public static void main(String[] args) {
        Track t;
        switch(args.length) {
            case 0:
                t = new Track("Watching the Wheels", new Duration(0,3,48));
                break;
            case 3:
                t = new Track(args[0], new Duration(0,
                        Integer.parseInt(args[1]),
                                Integer.parseInt(args[2])));
                        break;
            default:
                throw new IllegalArgumentException("Usage: Ex41Driver [title mins secs]");
        }
        System.out.println("t = " + t);

        // Show the "of" factory pattern
        Duration d2 = Duration.of(2700);
        LocalDate date = LocalDate.of(2024,05,15);
    }
}
