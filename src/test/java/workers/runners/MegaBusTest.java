package workers.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
@CucumberOptions(
        features = "src/test/java/resources/features/Test.feature",//path to the features
        glue="steps",
        tags = {"@run"})//what tags to incluse(@)/exclude(@~)
public class MegaBusTest extends AbstractTestNGCucumberTests{

}


