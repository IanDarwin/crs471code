public record Duration(int hours, int minutes, int seconds) {

    // Again, the above is all you really need. BUT, our application
    // has several places that need to construct Durations from total
    // seconds, so we implement the "of" pattern (just a naming convention):
    static Duration of(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int rem = totalSeconds - (hours * 3600);
        return new Duration(hours, rem / 60, rem % 60);
    }
}
