package StepDefinitionUser;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/UserPost.feature",
					 glue = {"StepDefinitionUser"},
					 tags = "@post",
monochrome=true,
dryRun = false
/*plugin = {"pretty","html:target/HtmlReports1/report.html",
  "json:target/JSONReports/report.json",
  "junit:target/JUnitReports/report.xml"}*/
)

public class Post_Runner {

}
