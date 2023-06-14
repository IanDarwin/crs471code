public record Duration(int hours, int minutes, int seconds) {
    static Duration of(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int rem = totalSeconds - (hours * 3600);
        return new Duration(hours, rem / 60, rem % 60);
    }
}
