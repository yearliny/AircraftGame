package ga.yuan.aircraft;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * 飞机类，拥有飞机的图片、坐标、飞行速度、角度等属性，并具有操控飞机移动的方法
 */

public class Aircraft extends GameObject {
    private BufferedImage img;
    private boolean live = true;
    private boolean up, down, left, right;

    Aircraft(String imgPath, double x, double y) {
        this.img = GameUtil.imgResize(GameUtil.getImage(imgPath), 50);
        this.x = x;
        this.y = y;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        speed = 7;
    }

    boolean isLive() {
        return live;
    }

    //如果活着就画出来飞机，如果死了则写出来Game Over。
    public void draw(Graphics g) {
        if (live) {
            g.drawImage(img, (int)x, (int)y, null);
            move();
        } else {
            Color thisColor = g.getColor();
            Font thisFont = g.getFont();
            g.setColor(Color.white);
            Font font = new Font("微软雅黑", Font.BOLD, 60);
            g.setFont(font);

            g.drawString("Game Over", 70, 250);

            g.setColor(thisColor);
            g.setFont(thisFont);
        }
    }

    private void move() {
        if (left) {
            x -= speed;
        }
        if (up) {
            y -= speed;
        }
        if (right) {
            x += speed;
        }
        if (down) {
            y += speed;
        }
    }

    void setLive(boolean live) {
        this.live = live;
    }

    //获取按键事件，因此设定移动属性
    void addDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: left = true; break;
            case KeyEvent.VK_UP: up = true; break;
            case KeyEvent.VK_RIGHT: right = true; break;
            case KeyEvent.VK_DOWN: down = true; break;
        }
    }

    //获取释放按键事件，因此取消移动属性
    void delDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: left = false; break;
            case KeyEvent.VK_UP: up = false; break;
            case KeyEvent.VK_RIGHT: right = false; break;
            case KeyEvent.VK_DOWN: down = false; break;
        }
    }
}
