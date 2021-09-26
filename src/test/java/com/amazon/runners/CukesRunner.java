package com.amazon.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt"
        },
        // providing a ContentRoot path to the features package
        features = "src/test/resources/features",
        // providing Source Root path to step_definitions package
        glue = "com/cybertek/step_definitions",
        dryRun = true,
        tags = "@wip"
)

public class CukesRunner {

}
