import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ResultsPage {
    public void getFromFirstTwenty() {
        WebDriverWait wait = new WebDriverWait(RedditHomePage.driver, 10);
        // Once you have entered the subreddit, locate the scrollable list that contains the posts
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.reddit.frontpage:id/link_list")));

        WebElement postList = RedditHomePage.driver.findElement(MobileBy.id("com.reddit.frontpage:id/link_list"));

        // Use the scroll method provided by Appium to scroll through the list until you have retrieved the first 20 posts
        int count = 0;
        WebElement mostUpvotedPost = null;
        int maxUpvotes = 0;
        while (count < 20) {
            List<WebElement> posts = postList.findElements(MobileBy.className("android.widget.LinearLayout"));
            for (WebElement post : posts) {
                WebElement upVotes = post.findElement(MobileBy.id("com.reddit.frontpage:id/upvote_button"));
                int upvoteCount = Integer.parseInt(upVotes.getText().replaceAll("[^0-9]", ""));
                System.out.println(upvoteCount);
                // Check if this post has more upvotes than the current maximum
                if (upvoteCount > maxUpvotes) {
                    mostUpvotedPost = post;
                    maxUpvotes = upvoteCount;
                }
                count++;
                System.out.println(count);
                if (count == 20) {
                    break;
                }
            }
            System.out.println(count + 1);
            RedditHomePage.driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().className(\"android.widget.LinearLayout\").instance(" + (count - 1) + "));"));
        }

        // Print the data for the most upvoted post
        if (mostUpvotedPost != null) {
            WebElement title = mostUpvotedPost.findElement(MobileBy.id("com.reddit.frontpage:id/title"));
            WebElement author = mostUpvotedPost.findElement(MobileBy.id("com.reddit.frontpage:id/username"));
            WebElement timestamp = mostUpvotedPost.findElement(MobileBy.id("com.reddit.frontpage:id/timestamp"));
            WebElement upvotes = mostUpvotedPost.findElement(MobileBy.id("com.reddit.frontpage:id/upvote_button"));
            System.out.println("Most upvoted post:");
            System.out.println("Title: " + title.getText());
            System.out.println("Author: " + author.getText());
            System.out.println("Timestamp: " + timestamp.getText());
            System.out.println("Upvotes: " + upvotes.getText());
        }

    }
}

