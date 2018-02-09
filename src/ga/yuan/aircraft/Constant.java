package ga.yuan.aircraft;

import java.awt.*;

/**
 * 游戏常量，窗口大小、位置等信息
 */

public class Constant {
    // 游戏窗口大小
    static final int GAME_HEIGHT = 710;
    static final int GAME_WIDTH = 480;

    //获取屏幕大小，因此生成游戏窗口位置
    private static int getLocation(String side) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        switch (side) {
            case "x": return (screenWidth/2) - (GAME_WIDTH/2);
            case "y": return (screenHeight/2) - (GAME_HEIGHT/2);
            default: return -1;
        }
    }
    //游戏窗口位置
    static final int GAME_LOCATION_X = getLocation("x");
    static final int GAME_LOCATION_Y = getLocation("y") - 10;
    //玩家初始化位置
    static final int PLAYER_INIT_LOCATION_X = 190;
    static final int PLAYER_INIT_LOCATION_Y = 450;
}
