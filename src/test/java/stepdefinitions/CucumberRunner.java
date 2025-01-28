package stepdefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/Features",
        glue = "stepdefinitions",
//        tags = @Login - only runs feature file with @Login annotation - filters the runs. Even if u only want specific scenarios, then only put that annotation
        plugin = {
            "pretty",
            "json:Report/cucumber.json",
            "junit:Report/cucumber.junit",
            "html:Report/cucumber.html"
        }
)
public class CucumberRunner {
}
