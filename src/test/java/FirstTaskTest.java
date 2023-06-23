import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class FirstTaskTest {

    

    @Test
    public void firstTaskTest() throws MalformedURLException, InterruptedException {
        RedditHomePage redditHomePage = new RedditHomePage();
        ResultsPage resultsPage = new ResultsPage();
        DetailsPage detailsPage = new DetailsPage();

        redditHomePage.openRedditApp();
        redditHomePage.clickSkipLogin();
        redditHomePage.searchForBanking();
//        redditHomePage.sortByHot();

        resultsPage.getFromFirstTwenty();

//        detailsPage.getDetails();
    }
}
