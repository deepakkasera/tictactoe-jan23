package com.scaler.tictactoe.factories;

import com.scaler.tictactoe.models.BotDifficultyLevel;
import com.scaler.tictactoe.strategies.botplayingstrategy.BotPlayingStrategy;
import com.scaler.tictactoe.strategies.botplayingstrategy.RandomBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyForEnum(BotDifficultyLevel difficultyLevel) {
        //For now we are hard coding it to return Random Playing Strategy always.
        return new RandomBotPlayingStrategy();
    }
}
