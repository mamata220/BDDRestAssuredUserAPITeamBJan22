package com.lms.api.cucumberrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/",
					 glue = {"com/lms/api/stepdef/user", "com/lms/api/stepdef/skillmap", "com/lms/api/stepdef/skills"},
					// tags = "@blank",
monochrome=true,
dryRun = false,
plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}

)
public class Runner {

}