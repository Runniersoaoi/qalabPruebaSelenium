package com.ntt.store.steps;

import com.ntt.store.core.DriverManager;
import com.ntt.store.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void setUp() {
        DriverManager.start();
    }

    @AfterStep
    public void captureStep(Scenario scenario) {
        ScreenshotUtils.attachTo(scenario);
    }

    @After
    public void tearDown() {
        DriverManager.quit();
    }
}
