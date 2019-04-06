package ga.yuan.aircraft;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

/**
 * 主窗口，继承自GameFrame类，负责项目主逻辑代码
 */

public class AircraftGameFrame extends GameFrame {

    private final Image background = GameUtil.getImage("images/background.png");
    private final Image boom = GameUtil.getImage("images/boom.gif");
    private final Aircraft player = new Aircraft("images/player.png", Constant.PLAYER_INIT_LOCATION_X, Constant.PLAYER_INIT_LOCATION_Y);
    private ArrayList<Bullet> bulletList = new ArrayList<>();
    private Date startDate, endDate;

    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null);
        player.draw(g);

        //since JDK1.8,forEach 循环
        bulletList.forEach((bullet) -> {
            bullet.draw(g);
            //碰撞检测代码，遍历所有子弹（矩形）和飞机是否相交
            boolean pong = bullet.getRectangle().intersects(player.getRectangle());
            if (player.isLive() && pong) {
                player.setLive(false);
                endDate = new Date();
            }
            if (!player.isLive()) {
                g.drawImage(boom, (int) player.x, (int) player.y, null);
                int period = (int) ((endDate.getTime() - startDate.getTime()) / 1000);
                printInfo(g, "你坚持了" + period + "秒", 109, 400, 51, Color.GRAY);
                printInfo(g, "你坚持了" + period + "秒", 110, 400, 50, Color.GREEN);
                switch (period / 10) {
                    case 0:
                        printInfo(g, "你的等级是菜鸟", 150, 600, 30, Color.GRAY);
                        break;
                    case 1:
                        printInfo(g, "你的等级是小鸟", 150, 600, 30, Color.GRAY);
                        break;
                    case 2:
                        printInfo(g, "你的等级是大鸟", 150, 600, 30, Color.GRAY);
                        break;
                    case 3:
                        printInfo(g, "你的等级是鸟人", 150, 600, 30, Color.GRAY);
                        break;
                    case 4:
                        printInfo(g, "你的等级是鸟王子", 150, 600, 30, Color.GRAY);
                        break;
                    case 5:
                        printInfo(g, "你的等级是神", 150, 600, 30, Color.GRAY);
                        break;
                    default:
                        printInfo(g, "你的等级是大神", 150, 600, 30, Color.GRAY);
                        break;
                }
            }
        });
        //since JDK5,增强for循环
//        for (Object bullet : bulletList) {
//            Bullet b = (Bullet) bullet;
//            b.draw(g);
//        }
        //普通循环
//        for (int i = 0; i < bulletList.size(); i++) {
//            Bullet b = (Bullet) bulletList.get(i);
//            b.draw(g);
//        }
    }

    private void printInfo(Graphics g, String str, int x, int y, int size, Color color) {
        //获取原属性
        Color thisColor = g.getColor();
        Font thisFont = g.getFont();
        //设定字体
        Font font = new Font("微软雅黑", Font.BOLD, size);
        g.setFont(font);
        g.setColor(color);

        g.drawString(str, x, y);

        //重置属性
        g.setFont(thisFont);
        g.setColor(thisColor);
    }

    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            player.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.delDirection(e);
        }
    }

    @Override
    public void launchFrame() {
        super.launchFrame();
        addKeyListener(new KeyMonitor());
        //设置标题
        setTitle("躲子弹大作战");
        startDate = new Date();
        //生成一堆子弹
        for (int i = 0; i < 30; i++) {
            Bullet b = new Bullet();
            bulletList.add(b);
        }
    }

    public static void main(String[] args) {
        new AircraftGameFrame().launchFrame();
    }
}
