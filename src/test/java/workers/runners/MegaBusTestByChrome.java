package workers.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features/megabus-booking.feature",
// path to the features
    glue = "steps", tags = {"@run","@chrome"})
// what tags to incluse(@)/exclude(@~)
public class MegaBusTestByChrome extends AbstractTestNGCucumberTests {

}
