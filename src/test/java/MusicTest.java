import items.Music;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;

@RunWith(JUnitParamsRunner.class)
public class MusicTest {
    private static final String VALID_BOOK_NAME = "GameOfThrones";
    private static final String VALID_BOOK_FORMAT = "Book";
    private static final int VALID_BOOK_YEAR = 1195;

    private static final String VALID_MUSIC_ARTIST = "Beatles";
    private static final String VALID_MUSIC_ARTIST_TWO = "ABBA";
    private static final String VALID_MUSIC_ARTIST_THREE = "AC/DC";


    private static final Object[] getMusic(){
        return $(
                $(new Music(VALID_BOOK_NAME, VALID_BOOK_FORMAT, VALID_BOOK_YEAR, VALID_MUSIC_ARTIST)),
                $(new Music(VALID_BOOK_NAME, VALID_BOOK_FORMAT, VALID_BOOK_YEAR, VALID_MUSIC_ARTIST_TWO)),
                $(new Music(VALID_BOOK_NAME, VALID_BOOK_FORMAT, VALID_BOOK_YEAR, VALID_MUSIC_ARTIST_THREE))
        );
    }

    private Music music;

    @Before
    public void setUp(){
        music = new Music(VALID_BOOK_NAME, VALID_BOOK_FORMAT, VALID_BOOK_YEAR, VALID_MUSIC_ARTIST);
    }

    @Test
    public void musicShouldHaveArtist() {
        //ARRANGE
        //ACT
        //ASSERT
        MusicAssert.assertThat(music).hasArtist(VALID_MUSIC_ARTIST);
    }


    @Test
    @Parameters(method = "getMusic")
    public void canCreateMusicWithParams(Music music){
        Assert.assertNotNull(music.getArtist());
    }
}
