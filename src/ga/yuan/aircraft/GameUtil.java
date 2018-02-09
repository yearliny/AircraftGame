package ga.yuan.aircraft;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * 游戏开发中常用的工具类，例如图片加载，图片缩放等静态方法
 */
public class GameUtil {
    private GameUtil() {} //工具类构造器通常私有

    public static BufferedImage getImage(String path) {
        BufferedImage img = null;
        try {
            URL u  = GameUtil.class.getClassLoader().getResource(path);
            assert u != null;
            img = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * 图片缩放方法
     * @param originalImg 目标图片
     * @param maxWidth 设定图片的最大宽度，并依照原比例缩放
     * @return 返回一个缩放后的 BufferedImage 对象
     */
    public static BufferedImage imgResize(BufferedImage originalImg, int maxWidth) {
        double newImgHeight = (double)maxWidth/originalImg.getWidth(null) *
                originalImg.getHeight(null);
        BufferedImage newImage = new BufferedImage(maxWidth, (int)newImgHeight, originalImg.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImg, 0,0,maxWidth, (int)newImgHeight,null);
        g.dispose();
        return newImage;
    }

}
