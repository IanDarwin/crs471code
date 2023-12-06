public record Track(String title, Duration runningTime) {

    // The "get" methods are named title() not getTitle(), etc.
    // If you need the old-style names for compatibility, just write
    // trivial delegation methods:
    public String getTitle() {
        return title;
    }

    // And, to add to our woes, musicians and/or record companies sometimes
    // have to change names to reflect modern sensibilities, hence the "with" pattern
    Track withTitle(String newTitle) {
        return new Track(newTitle, runningTime());
    }
}
