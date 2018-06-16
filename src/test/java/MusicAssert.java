import items.Music;
import org.fest.assertions.api.AbstractAssert;
import org.fest.assertions.api.Assertions;

public class MusicAssert extends AbstractAssert<MusicAssert, Music> {
    public MusicAssert(Music actual) {
        super(actual, MusicAssert.class);
    }

    public static MusicAssert assertThat(Music actual) {
        return new MusicAssert(actual);
    }

    //Specific assertion for author
    public MusicAssert hasArtist(String artist) {
        // check that actual Book we want to make assertions on is not null.
        isNotNull();

        // use of existing Fest assertions but replacing the error message (format specifier are supported)
        Assertions.assertThat(actual.getArtist())
                .overridingErrorMessage("Expected Music's artist to be <%s> but was <%s>", artist, actual.getArtist())
                .isEqualTo(artist);

        // another option : throwing directly an assertion error if the expected condition is not met
        String errorMessage = String.format("Expected music's artist to be <%s> but was <%s>", artist, actual.getArtist());
        if (!actual.getArtist().equals(artist)) { throw new AssertionError(errorMessage); }

        return this; // return the current assertion for method chaining
    }

}
