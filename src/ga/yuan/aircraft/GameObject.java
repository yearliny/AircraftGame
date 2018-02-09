package ga.yuan.aircraft;

import java.awt.*;

/**
 * GameObject 是游戏中所有移动物体的抽象类，包含了坐标、速度、宽度、高度。
 * 还有一个返回 Rectangle 对象的方法。
 */

public abstract class GameObject {
    double x, y;
    int speed;
    int width, height;

    GameObject() { }

    public Rectangle getRectangle(){
        return new Rectangle((int)x, (int)y, width, height);
    }

    abstract public void draw(Graphics g);
}
