package ga.yuan.aircraft;

import java.awt.*;

/**
 * 子弹类
 */

public class Bullet extends GameObject{
    private double degree;

    Bullet() {
        this.x = Constant.GAME_WIDTH/2;
        this.y = 30;
        this.degree = Math.random() * Math.PI * 2;
    }

    public void draw(Graphics g) {
        //定义子弹的大小和速度
        width = 8;
        height = 8;
        speed = 4;
        //绘制移动的子弹
        Color thisColor = g.getColor();
        g.setColor(Color.darkGray);
        g.fillOval((int)x, (int)y, width, height);
        x += speed * Math.cos(degree);
        y += speed * Math.sin(degree);
        //子弹碰壁检测并反弹
        if (x <0 || x>= Constant.GAME_WIDTH) {
            degree = Math.PI - degree;
        }
        if (y < 30 || y >= Constant.GAME_HEIGHT) {
            degree = -degree;
        }
        //充值颜色
        g.setColor(thisColor);
    }
}
