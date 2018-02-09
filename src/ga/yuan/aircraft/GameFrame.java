package ga.yuan.aircraft;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 测试游戏窗口
 * @author yearliny
 */
public class GameFrame extends Frame{
    /**
     * 双缓冲实现代码，消除闪屏
     */
    private static Image offScreenImage = null;
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
//        Color c = gOffScreen.getColor();
//        gOffScreen.setColor(Color.GREEN);
//        gOffScreen.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
//        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }
    public void launchFrame () {
        setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        setLocation(Constant.GAME_LOCATION_X, Constant.GAME_LOCATION_Y);
        setVisible(true);

        new PaintThread().start();

        //重写匿名内部类 windowClosing，System.exit(0)表示正常关闭，传入参数负数代表异常关闭
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void paint(Graphics g) {
    }

    // 定义一个重画窗口的线程类（内部类：方便访问外部类属性 eg.repaint）
    class PaintThread extends Thread {
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(40);  //每秒重绘25次
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
