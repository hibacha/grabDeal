package workers.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features/megabus-booking.feature",
// path to the features
    glue = "steps", tags = {"@run","@firefox"})
// what tags to incluse(@)/exclude(@~)
public class MegaBusTestByFirefox extends AbstractTestNGCucumberTests {

}
