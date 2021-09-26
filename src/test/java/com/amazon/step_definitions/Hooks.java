package com.amazon.step_definitions;

import com.amazon.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    @After
    public void tearDownScenario(Scenario scenario) {
        // Scenario scenario - tracks info regarding the current scenario

        // if scenario failed, take a screen shot
        if(scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            // we have to store screenshot in array of bytes
            // gets a screen shot as bytes

            scenario.attach(screenshot, "image/png", scenario.getName());
            // scenario.attach() takes 3 args
            // 1. screen shot array of bytes
            // 2. image file type
            // 3. name of the scenario
        }
        Driver.closeDriver();
    }
}
