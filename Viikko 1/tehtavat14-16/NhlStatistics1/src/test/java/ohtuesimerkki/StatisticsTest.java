package ohtuesimerkki;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test 
    public void teamReturnsCorrectList() {
        List<Player> players2 = stats.team("PIT");
        Player correct = new Player("Lemieux", "PIT", 45, 54);
        assertEquals(players2.get(0).toString(), correct.toString());
    }

    @Test
    public void searchPlayerReturnsCorrectPlayer() {
        Player player = stats.search("Semenko");
        Player correct = new Player("Semenko", "EDM", 4, 12);
        assertEquals(player.toString(), correct.toString());
    }

    @Test
    public void searchReturnsNullCorrectly() {
        Player player = stats.search("Jaakko");
        assertTrue(player==null);
    }

    @Test
    public void topScoresReturnsTop1() {
        Player player = stats.topScorers(1).get(0);
        Player correct = new Player("Gretzky", "EDM", 35, 89);
        assertEquals(player.toString(), correct.toString());
    }
}