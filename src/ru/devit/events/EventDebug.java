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

    public static void main(String [] args){
        //загрузка пользователей
        Users.getAllUsersFromDB();
        event = new TestEvent(Users.getUserForId(11));
        step();
    }

    private static void step() {
        Event.Stepping step = event.getStep();
        out.println();
        out.println(step.getDescription());
        int i = 0;
        for ( HashMap opt : step.getOptions() ) {
            i++;
            out.println( i + ". " +opt.get("txt") );
        }

        int answer = getAnswer()-1;

        HashMap opt = step.getOption(answer);
        Resources res = (Resources) opt.get("resources");
        if ( res.getNeedInput() == true ) {
            for (Map.Entry<String, Resources.Resource> entry : res.getAllResources().entrySet() ) {
                if ( entry.getValue().getCount() == -1 ) {
                    res.getResource(entry.getKey()).setCount( getResourceCount( "Введите кол-во ресурса (" + res.getResource(entry.getKey()).getName() + ")" ) );
                }
            }
            event.setStep(answer);
        } else {
            event.setStep(answer);
        }
        step();
    }

    private static int getAnswer(){
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
