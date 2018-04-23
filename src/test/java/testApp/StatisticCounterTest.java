package testApp;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import testApp.logic.StatisticCounter;
import org.junit.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@RunWith(Parameterized.class)
public class StatisticCounterTest {

    private String line;
    private int expectedLineLength;
    private double averageWordLength;
    private BigDecimal expectedAverageWordLength;
    private String expectedShortestWord;
    private String expectedLongestWord;
    private StatisticCounter statisticCounter;
    private List<String> input;


    public StatisticCounterTest(String line,
                                int expectedLineLength,
                                double averageWordLength,
                                String expectedShortestWord,
                                String expectedLongestWord) {
        this.line = line;
        this.expectedLineLength = expectedLineLength;
        this.averageWordLength = averageWordLength;
        this.expectedShortestWord = expectedShortestWord;
        this.expectedLongestWord = expectedLongestWord;

    }


    @Parameters
    public static Collection<Object[]> data() {

        return Arrays.asList(new Object[][] {
                { "Pede fusce ultrices, in lorem pretium mauris proin", 50, 5.38, "in", "ultrices,"  },
                { "Lorem ipsum dolor sit amet, consectetur", 39, 5.67, "sit", "consectetur" },
                { "", 0, 0, "", "" },
                { "Hello", 5, 5, "Hello", "Hello" },

        });
    }

    @Before
    public void prepare() {
        statisticCounter = new StatisticCounter();
        expectedAverageWordLength = new BigDecimal(averageWordLength,
                new MathContext(3, RoundingMode.HALF_EVEN));
        input = Arrays.asList(line.split(" "));
    }

    @After
    public void clean() {
        statisticCounter = null;
    }


    @Test
    public void testLineLength() {
        assertThat(statisticCounter.lineLength(line), is(equalTo(expectedLineLength)));
    }

    @Test
    public void testAverageWordLength() {
        assertThat(statisticCounter.averageWordLength(input), is(equalTo(expectedAverageWordLength)));
    }

    @Test
    public void testShortestWord() {
        assertThat(statisticCounter.shortestWord(input), is(equalTo(expectedShortestWord)));
        assertThat(statisticCounter.shortestWord(input), not(equalTo(null)));
    }

    @Test(expected = NullPointerException.class)
    public void testShortestWord_throwsException() throws NullPointerException {
        statisticCounter.shortestWord(null);
    }

    @Test
    public void testLongestWord() {
        assertThat(statisticCounter.longestWord(input), is(equalTo(expectedLongestWord)));
        assertThat(statisticCounter.longestWord(input), not(equalTo(null)));
    }

    @Test(expected = NullPointerException.class)
    public void testLongestWord_throwsException() throws NullPointerException {
        statisticCounter.longestWord(null);
    }

}
