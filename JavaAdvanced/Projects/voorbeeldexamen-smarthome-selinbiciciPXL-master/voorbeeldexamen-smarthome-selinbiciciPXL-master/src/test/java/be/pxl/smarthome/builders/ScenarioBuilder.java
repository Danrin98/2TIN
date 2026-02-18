package be.pxl.smarthome.builders;

import be.pxl.smarthome.domain.Action;
import be.pxl.smarthome.domain.Scenario;

import java.util.ArrayList;
import java.util.List;

public class ScenarioBuilder {

    private String name;

    private List<Action> actions = new ArrayList<>();

    public ScenarioBuilder() {
    }

    public static ScenarioBuilder aScenario() {return new ScenarioBuilder();}

    public ScenarioBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ScenarioBuilder withActions(List<Action> actions) {
        this.actions = actions;
        return this;
    }

    public Scenario build() {
        Scenario scenario = new Scenario();
        scenario.setName(name);
        for(Action action : actions) {
            scenario.addAction(action);
        }
        return scenario;
    }
}
