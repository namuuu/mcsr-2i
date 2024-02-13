package fr.namu.mcsr2i.object;

import fr.namu.mcsr2i.enumerator.GameStateEnum;
import org.bukkit.entity.Player;

import javax.xml.stream.Location;

public class GameData {

        private GameStateEnum gameState = GameStateEnum.PREGAME;
        private boolean gracePeriod = true;
        private Player lastBedUser = null;

        private static GameData instance;



        public GameData() {
            instance = this;
        }


        public static GameData getInstance() {
        return instance;
    }
        public GameStateEnum getGameState() {
            return this.gameState;
        }

        public boolean isState(GameStateEnum state) {
            return this.gameState == state;
        }
        public void setGameState(GameStateEnum state) {
            this.gameState = state;
        }

        public Player getLastBedUser() {
            return this.lastBedUser;
        }
        public void setLastBedUser(Player player) {
            this.lastBedUser = player;
        }

        public boolean isGracePeriod() {
            return this.gracePeriod;
        }
        public void setGracePeriod(boolean state) {
            this.gracePeriod = state;
        }

}
