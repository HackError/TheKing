package ru.devit.events;

import ru.devit.User;
import ru.devit.gameObjects.Resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by georgys on 28.04.2015.
 */
public abstract class Event {
    protected String eventName = "";
    protected int step = 0;
    protected ArrayList<Integer> steps = new ArrayList<Integer>();
    protected List<Stepping> stepping = new ArrayList<Stepping>();
    protected User user = null;
    protected final Random random = new Random();

    /**
     * Конструктор, принимает пользователя
     * @param user
     */
    public Event( User user ) {
        this.user = user;
    }

    /**
     * Возвращает имя события
     * @return
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * устанавливает имя события
     * @param eventName
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Переход на следующий шаг
     */
    public abstract void nextStep();

    public Stepping getStep()
    {
        return stepping.get(step);
    }

    /**
     * Устанавливает шаги
     * @param step
     */
    public void addStep(Stepping step)
    {
        stepping.add(step);
    }

    /**
     * Устанавливает следующий шаг
     * @param answer
     */
    public void setStep( int answer )
    {
        if ( stepping.get(this.step).getOptions().size() >= answer ) {
            stepping.get(this.step).setAnswer(answer);
            this.step = (int) stepping.get(this.step).getOption(answer).get("goto");
        }
    }

    /**
     * Класс шага
     */
    protected class Stepping {
        private ArrayList<HashMap> options = new ArrayList<HashMap>();
        private int step = 0;
        private String description = "";
        private int answer;

        /**
         * Конструктор
         * принимает номер шага и текстовое описание
         * @param step
         * @param description
         */
        public Stepping(int step, String description)
        {
            this.step = step;
            this.description = description;
        }

        public String getDescription()
        {
            return description;
        }

        /**
         * Устанавливает ответ на текущий шаг
         * @param answer
         */
        public void setAnswer(int answer)
        {
            this.answer = answer;
        }

        /**
         * Устанавливает ответ на текущий шаг и используемые ресурсы для шага
         * @param answer
         * @param resource
         * @param count
         */
        public void setAnswer(int answer, String resource, int count)
        {
            this.answer = answer;
            if ( user.getUserResourceByRes(resource) >= count )
                options.get(answer).put(resource, count);
            else
                options.get(answer).put(resource, 0);
        }

        /**
         * Устанавливает варианты действий на текущий шаг
         * @param txt
         * @param goToStep
         */
        public void setOptions( String txt, int goToStep )
        {
            HashMap option = new HashMap<String,Object>();
            option.put("txt", txt);
            option.put("goto", goToStep);
            options.add(option);
        }

        /**
         * Устанавливает варианты действий на текущий шаг и используемые ресурсы
         * @param txt
         * @param goToStep
         * @param res
         */
        public void setOptions( String txt, int goToStep, Resources res )
        {
            HashMap option = new HashMap<String,Object>();
            option.put("txt", txt);
            option.put("goto", goToStep);
            option.put("resources", (Resources)res);
            options.add(option);
        }

        public HashMap getOption( int option )
        {
            return options.get(option);
        }

        public ArrayList<HashMap> getOptions()
        {
            return options;
        }

    }
}
