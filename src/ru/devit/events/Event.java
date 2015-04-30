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
    //газвание квеста
    protected String eventName = "";
    //текущий шаг
    protected int step = 0;
    //выбранные шаги
    protected ArrayList<Integer> steps = new ArrayList<Integer>();
    //массив событий квеста
    protected List<Stepping> stepping = new ArrayList<Stepping>();
    //игрок
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
    public boolean nextStep(int answer)
    {
        steps.add(step);
        if ( stepping.get(this.step).getOptions().size() >= answer ) {
            stepping.get(this.step).setAnswer(answer);
            this.step = stepping.get(this.step).getOption(answer).getGotoStep();
        }
        if ( this.step == -100 ) {
            return true;
        }
        return false;
    }

    /**
     * возвращает текущий шаг
     * @return
     */
    public Stepping getStep()
    {
        return stepping.get(step);
    }

    /**
     * возвращает шаг по номеру
     * @param step
     * @return
     */
    public Stepping getStep(int step)
    {
        return stepping.get(step);
    }

    /**
     * Добавляет шаги
     * @param step
     */
    public void addStep(Stepping step)
    {
        stepping.add(step.getStep(), step);
    }



    /**
     * Класс шага
     */
    protected class Stepping {
        private ArrayList<Option> options = new ArrayList<Option>();
        private int step = 0;
        private String description = "";
        private int answer;

        public int getStep() {
            return step;
        }

        public int getAnswer() {
            return answer;
        }

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
            /*
            this.answer = answer;
            if ( user.getUserResourceByRes(resource) >= count )
                options.get(answer).put(resource, count);
            else
                options.get(answer).put(resource, 0);
                */
        }

        /**
         * Устанавливает варианты действий на текущий шаг
         * @param txt
         * @param goToStep
         */
        public void setOptions( String txt, int goToStep )
        {
            Option option = new Option();
            option.setTxt(txt);
            option.setGotoStep(goToStep);
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
            Option option = new Option();
            option.setTxt(txt);
            option.setGotoStep(goToStep);
            option.setResources(res);
            options.add(option);
        }

        public Option getOption( int option )
        {
            return options.get(option);
        }

        public ArrayList<Option> getOptions()
        {
            return options;
        }

        /**
         * КЛАСС ОПЦИЙ ШАГА
         */
        class Option {
            String txt = "";
            int gotoStep = 0;
            Resources resources = new Resources();

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            public int getGotoStep() {
                return gotoStep;
            }

            public void setGotoStep(int gotoStep) {
                this.gotoStep = gotoStep;
            }

            public Resources getResources() {
                return resources;
            }

            public void setResources(Resources resources) {
                this.resources = resources;
            }
        }
    }
}
