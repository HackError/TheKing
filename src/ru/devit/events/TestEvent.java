package ru.devit.events;

import ru.devit.User;
import ru.devit.gameObjects.Resources;

import java.util.HashMap;

import static java.lang.Math.*;

/**
 * Created by georgys on 28.04.2015.
 */
public class TestEvent extends Event {

    Resources looseRes = null;
    Resources giveRes = null;

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

        Stepping st = null;

        switch (this.step) {
            case 0:
                //определим задействованные ресурсы
                looseRes = new Resources();
                //при провале квеста игрок потеряет это кол-во зерна
                looseRes.setGrain(user.getUserResources().getGrain() / 100 * (random.nextInt(40) + 1));
                //при покупке средства от саранчи, игрок потеряет столько денег
                looseRes.setMoney(user.getUserResources().getMoney() / 100 * (random.nextInt(5) + 1) );
                //нулевой шаг, инициализация квеста
                st = new Stepping(step, "Мой Царь! На наши поля надвигаются полчища саранчи. " + "" +
                        "Если ничего не предпринять мы потеряем " + looseRes.getGrain() + " тонн наших запасов зерна.");
                //добавим варианты ответов
                //первый вариант
                st.setOptions("Направить войска в размере [input=text name=soldiers size=2] солдат(а)", 1, new Resources("soldiers", -1));
                //второй вариант
                st.setOptions("Приобрести заморское средство от саранчи за " + looseRes.getMoney() + " монет" , 2, new Resources());
                //третий вариант
                st.setOptions("Ничего не делать, пусть жрут, авось подавяться!", 3, new Resources());
                break;
            case 1:
                //Направить войска
                int inputSoldiers  = (int) this.getStep(0).getOption(0).getResources().getResource("soldiers").getCount();
                //проверим кол-во введённых солдат
                if ( inputSoldiers > user.getUserResources().getSoldiers() )
                    //если введённое кол-во больше чем у пользователя, установим максимальное кол-во
                    this.getStep(0).getOption(0).getResources().setSoldiers(user.getUserResources().getSoldiers());
                else if ( inputSoldiers <= 0 ) {
                    this.step = 0;
                    this.stepping.get(0).removeOption(0);
                    break;
                }
                //зарандомим кол-во потерянных солдат
                looseRes.setSoldiers( (float)random.nextInt( Math.round(this.getStep(0).getOption(0).getResources().getSoldiers())) + 1 );
                //отнимем загрызанных солдат из ресуров игрока
                user.getUserResources().setSoldiers((user.getUserResources().getSoldiers() - looseRes.getSoldiers()));
                st = new Stepping(step, "Ваши солдаты в количестве " + this.getStep(0).getOption(0).getResources().getSoldiers() +
                        " прибыли на поля для помощи крестьянам в борьбе с саранчей. И всё бы ничего, но " + looseRes.getSoldiers() + " солдат(а) были зверски загрызены саранчёй. " +
                        "Зато зерно удалось спасти...");
                st.setOptions("Завершить квест", -100);
                break;
            case 2:
                //Приобрести заморское средство от саранчи
                user.getUserResources().setMoney(user.getUserResources().getMoney() - looseRes.getMoney());
                //узнаем помогло или нет
                int help = random.nextInt(100) + 1;
                if ( help < 30 ) {
                    user.getUserResources().setGrain( ( user.getUserResources().getGrain() - looseRes.getGrain() ) );
                    st = new Stepping(step, "Вы купили заморское средство за " + looseRes.getMoney() + " монет, но оно не помогло");
                    st.setOptions("Завершить квест", -100);
                } else {
                    st = new Stepping(step, "Вы купили заморское средство за " + looseRes.getMoney() + " монет и саранча в ужасе бежала с вашей земли");
                    st.setOptions("Завершить квест", -100);
                }
                break;
            case 3:
                //ничего не делать
                user.getUserResources().setGrain( ( user.getUserResources().getGrain() - looseRes.getGrain() ) );
                st = new Stepping(step, "Вы решили ничего не делать и в результате саранча пожрала ваши запасы зерна");
                st.setOptions("Завершить квест", -100);
                break;
        }
        addStep(st);
        return false;
    }


}
