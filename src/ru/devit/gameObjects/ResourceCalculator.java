package ru.devit.gameObjects;

import ru.devit.GameConfig;
import ru.devit.Server;
import ru.devit.User;

/**
 * КЛАСС КАЛЬКУЛЯТОР РЕСУРСА
 * Created by georgys on 11.05.2015.
 */
public class ResourceCalculator {

    Resources resources = null;
    User user = null;
    GameConfig gameConfig = null;

    public ResourceCalculator( User user)
    {
        this.user = user;
        resources = new Resources(user.getUserResources());
        this.gameConfig = Server.getGameConfig();
    }

    public void calc()
    {
        user.getUserResources().setGrain( calcGrain() );
        user.getUserResources().setWood(calcWood());
        user.getUserResources().setGold(calcGold());
    }

    /**
     * калькуляция прироста пшеницы за ход
     * TODO: необходимо сделать зависимость от размера имеющейся земли у игрока и кол-ва выделенных крестьян на обработку земли
     */
    private Float calcGrain()
    {
        //кол-во зерна генерируемого 1 крестьянином за ход
        Float grReg = gameConfig.getGrainRegeneration();
        //кол-во имеющегося зерна
        Float grain = resources.getGrain();
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

    private Float calcWood()
    {
        Float woodReg = gameConfig.getWoodRegeneration();
        Float wood = resources.getWood();
        int userPeasantInWork = user.getUserPeasantWork().getWood();

        return wood + ( userPeasantInWork * woodReg );
    }

    private Float calcGold()
    {
        Float goldReg = gameConfig.getGoldRegeneration();
        Float gold = resources.getGold();
        int userPeasantInWork = user.getUserPeasantWork().getGold();

        return gold + ( userPeasantInWork * goldReg );
    }

}
