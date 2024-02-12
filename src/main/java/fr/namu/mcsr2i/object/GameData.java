package fr.namu.mcsr2i.object;

import fr.namu.mcsr2i.enumerator.GameStateEnum;

public class GameData {

        private GameStateEnum gameState = GameStateEnum.PREGAME;

        private static GameData instance;

        public static GameData getInstance() {
            return instance;
        }

        public GameData() {
            instance = this;
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

}
