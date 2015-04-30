package ru.devit.events;

import ru.devit.User;
import ru.devit.gameObjects.Resources;

import static java.lang.Math.*;

/**
 * Created by georgys on 28.04.2015.
 */
public class TestEvent extends Event {

    public TestEvent(User user) {
        super(user);
        setEventName("САРАНЧА");

        int finalLooseGrain = round(user.getUserResources().getGrain() / 100 * (random.nextInt(40) + 30));

        Stepping step = new Stepping(0, "Мой Царь! На наши поля надвигаются полчища саранчи. " + "" +
                "Если ничего не предпринять мы потеряем " + finalLooseGrain + " тонн наших запасов зерна.");
        step.setOptions("Направить войска в размере [input=text name=soldiers size=2] солдат(а)", 1, new Resources("soldiers", -1));
        step.setOptions("Приобрести заморское средство от саранчи", 2, new Resources());
        step.setOptions("Ничего не делать, пусть жрут, авось подавяться!", 3, new Resources());
        addStep(step);

        step = new Stepping(1, "Ваши солдаты в количестве n прибыли на поля для помощи крестьянам в борьбе с саранчей. И всё бы ничего, но n солдат(а) были зверски загрызены саранчёй.");
        addStep(step);

    }

    @Override
    public void nextStep() {

    }
}
