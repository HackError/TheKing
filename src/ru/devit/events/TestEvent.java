package ru.devit.events;

import ru.devit.User;
import ru.devit.gameObjects.Resources;

import java.util.HashMap;

import static java.lang.Math.*;

/**
 * Created by georgys on 28.04.2015.
 */
public class TestEvent extends Event {

    HashMap loose = new HashMap();
    HashMap give = new HashMap();

    public TestEvent(User user) {
        super(user);
        setEventName("САРАНЧА");
        nextStep(-1);
    }
    @Override
    public boolean nextStep(int answer)
    {
        /*Если это не начальная инициализация квеста, то...*/
        if ( answer > -1 ) {
            //
            steps.add(step);
            if (stepping.get(this.step).getOptions().size() >= answer) {
                stepping.get(this.step).setAnswer(answer);
                this.step = stepping.get(this.step).getOption(answer).getGotoStep();
            }
        }

        if ( this.step == -100 ) {
            return true;
        }

        switch (this.step) {
            case 0:
                loose.put("finalLooseGrain", round(user.getUserResources().getGrain() / 100 * (random.nextInt(40) + 1)));
                Stepping step = new Stepping(0, "Мой Царь! На наши поля надвигаются полчища саранчи. " + "" +
                        "Если ничего не предпринять мы потеряем " + loose.get("finalLooseGrain") + " тонн наших запасов зерна.");
                step.setOptions("Направить войска в размере [input=text name=soldiers size=2] солдат(а)", 1, new Resources("soldiers", -1));
                step.setOptions("Приобрести заморское средство от саранчи", 2, new Resources());
                step.setOptions("Ничего не делать, пусть жрут, авось подавяться!", 3, new Resources());
                addStep(step);
                break;
            case 1:
                int ls = random.nextInt((int) this.getStep(0).getOption(0).getResources().getResource("soldiers").getCount()) + 1;
                user.getUserResources().setSoldiers((user.getUserResources().getSoldiers() - ls));
                Stepping st = new Stepping(1, "Ваши солдаты в количестве " + this.getStep(0).getOption(0).getResources().getResource("soldiers").getCount() +
                        " прибыли на поля для помощи крестьянам в борьбе с саранчей. И всё бы ничего, но " + ls + " солдат(а) были зверски загрызены саранчёй. " +
                        "Зато зерно удалось спасти...");
                st.setOptions("Завершить квест", -100);
                addStep(st);
                break;
        }

        return false;
    }


}
