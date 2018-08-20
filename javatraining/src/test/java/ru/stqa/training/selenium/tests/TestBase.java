package ru.stqa.training.selenium.tests;

import org.junit.After;
import org.junit.Before;
import ru.stqa.training.selenium.app.Application;

public class TestBase {
    public Application app;

    @Before
    public void start(){
        app = new Application();
    }

    @After
    public void stop(){
        app.quit();
    }
}
