package ru.devit.events;

import ru.devit.Users;
import ru.devit.gameObjects.Resources;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Created by user on 29.04.2015.
 */
public class EventDebug {

    static Event event = null;
    static Users users = new Users();

    public static void main(String [] args){
        event = new TestEvent(users.getUserForId(11));
        step();
    }

    private static void step() {
        Event.Stepping step = event.getStep();
        out.println();
        out.println(step.getDescription());
        int i = 0;
        for ( Event.Stepping.Option opt : step.getOptions() ) {
            i++;
            out.println( i + ". " +opt.getTxt() );
        }

        int answer = getAnswer()-1;

        Event.Stepping.Option opt = step.getOption(answer);
        Resources res = opt.getResources();
        if ( res.getNeedInput() == true ) {
            for (Map.Entry<String, Resources.Resource> entry : res.getAllResources().entrySet() ) {
                if ( entry.getValue().getCount() == -1 ) {
                    res.getResource(entry.getKey()).setCount( getResourceCount( "Введите кол-во ресурса (" + res.getResource(entry.getKey()).getName() + ")" ) );
                }
            }
        }

        if (event.nextStep(answer) == true)
            return;

        step();
    }

    private static int getAnswer(){
        out.print("Ваш выбор: ");
        Scanner in = new Scanner(System.in);
        int ret = in.nextInt();
        return ret;
    }

    private static int getResourceCount( String str )
    {
        out.println(str);
        Scanner in = new Scanner(System.in);
        int ret = in.nextInt();
        return ret;
    }
}
