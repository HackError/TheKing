package ru.devit.gameObjects;

import ru.devit.GameConfig;
import ru.devit.Server;
import ru.devit.User;

/**
 * КЛАСС КАЛЬКУЛЯТОР РЕСУРСА
 * Created by georgys on 11.05.2015.
 */
public class ResourceCalculator {

    private static Resources resources = null;
    private static User user = null;
    private static GameConfig gameConfig = null;

    /*
    public ResourceCalculator( User user)
    {
        this.user = user;
        resources = new Resources(user.getUserResources());
        this.gameConfig = Server.getGameConfig();
    }
    */

    public static void calc( User user )
    {
        ResourceCalculator.user = user;
        resources = new Resources(user.getUserResources());
        gameConfig = Server.getGameConfig();
        user.getUserResources().setGrain( calcGrain() );
        user.getUserResources().setWood(calcWood());
        user.getUserResources().setGold(calcGold());
        ResourceCalculator.user = null;
        resources = null;
        gameConfig = null;
    }

    /**
     * калькуляция прироста пшеницы за ход
     * TODO: необходимо сделать зависимость от размера имеющейся земли у игрока и кол-ва выделенных крестьян на обработку земли
     */
    private static Float calcGrain()
    {
        //кол-во зерна генерируемого 1 крестьянином за ход
        float grReg = gameConfig.getGrainRegeneration();
        //кол-во имеющегося зерна
        float grain = resources.getGrain();
        //кол-во крестьян задействованных в обработке земли
        int userPeasantInWork = user.getUserPeasantWork().getGrain();
        //общее кол-во крестьян
        float peasant = resources.getPeasant();
        //кол-во зерна потребляемого крестьянином
        float peasantEatGrain = gameConfig.getGrainEatingPeasant();
        //кол-во солдат
        float soldiers = resources.getSoldiers();
        //кол-во зерна потребляемого солдатом
        float soldierEatGrain = gameConfig.getGrainEatingSoldier();
        //resources.setGrain( grain + ( userPeasantInWork * grReg ) );
        return grain + ( userPeasantInWork * grReg ) - ( peasant * peasantEatGrain ) - ( soldiers * soldierEatGrain );
    }

    private static Float calcWood()
    {
        float woodReg = gameConfig.getWoodRegeneration();
        float wood = resources.getWood();
        int userPeasantInWork = user.getUserPeasantWork().getWood();

        return wood + ( userPeasantInWork * woodReg );
    }

    private static Float calcGold()
    {
        float goldReg = gameConfig.getGoldRegeneration();
        float gold = resources.getGold();
        int userPeasantInWork = user.getUserPeasantWork().getGold();

        return gold + ( userPeasantInWork * goldReg );
    }

}
