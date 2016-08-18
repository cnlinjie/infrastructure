package com.github.cnlinjie.infrastructure.util;


import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ImageUtils {

    /**
     * 缩放图像（按高度和宽度缩放）
     *
     * @param srcImageFile 源图像文件地址
     * @param height       缩放后的高度
     * @param width        缩放后的宽度
     */
    public final static BufferedImage scale (String srcImageFile, int height, int width) {
        try {
            return scale(ImageIO.read(new File(srcImageFile)),height,width);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }


    /**
     * 缩放图像（按高度和宽度缩放）
     * @param src
     * @param height       缩放后的高度
     * @param width        缩放后的宽度
     */
    public final static BufferedImage scale (BufferedImage src, int height, int width) {
        BufferedImage rebi = null;
        double ratio = 0.0; // 缩放比例
        int biHeight = src.getHeight();
        int biWidth = src.getWidth();
        rebi = new BufferedImage(width, height, BufferedImage.SCALE_SMOOTH);
        // 计算比例
        if ((biHeight > height) || (biWidth > width)) {
            if (biHeight > biWidth) {
                ratio = (new Integer(height)).doubleValue() / biHeight;
            } else {
                ratio = (new Integer(width)).doubleValue() / biWidth;
            }
        } else {
            if (height > width) {
                ratio = (new Integer(height)).doubleValue() / biHeight;
            } else {
                ratio = (new Integer(width)).doubleValue() / biWidth;
            }
        }
        RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), getMyRenderingHints());
        rebi = op.filter(src, null);
        return rebi;
    }

    /**
     * 旋转图片为指定角度
     *
     * @param bufferedimage 目标图像
     * @param degree        旋转角度
     * @return
     */
    public static BufferedImage rotateImage (final BufferedImage bufferedimage, final int degree) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }

    /**
     * 大就裁剪，小就平铺
     *
     * @param path
     * @param width
     * @param height
     */
    public static BufferedImage cutImage (String path, int width, int height) {
        String fileType = path.substring(path.lastIndexOf('.') + 1);
        File file = new File(path);
        FileInputStream is = null;
        ImageInputStream iis = null;
        BufferedImage newBi = null;
        try {
            BufferedImage bi = ImageIO.read(file);
            int _width = bi.getWidth();
            int _height = bi.getHeight();
            if (width > _width || height > _height) {
                newBi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D g2d = newBi.createGraphics();
                for (int i = 0; i < (width); i += _width) {
                    for (int j = 0; j < (height); j += _height) {
                        g2d.drawImage(bi, i, j, null, null);
                    }
                }
            } else {
                is = new FileInputStream(file);
                Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(fileType);
                ImageReader reader = it.next();
                iis = ImageIO.createImageInputStream(is);
                reader.setInput(iis, true);
                ImageReadParam param = reader.getDefaultReadParam();
                Rectangle rect = new Rectangle(0, 0, width, height);
                param.setSourceRegion(rect);
                newBi = reader.read(0, param);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (is != null)
                    is.close();
                if (iis != null)
                    iis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newBi;
    }

    /**
     * 合成图片
     *
     * @param imgobject
     * @param width
     * @param height
     * @throws IOException
     */
    public static BufferedImage mergeImg (BufferedImages imgobject[], int width, int height) {
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        int len = imgobject.length;
        Graphics2D g2d[] = new Graphics2D[imgobject.length];
        for (int z = 0; z < len; z++) {
            g2d[z] = tag.createGraphics();
            g2d[z].drawImage(imgobject[z].getBufferedImage(), imgobject[z].getX(), imgobject[z].getY(), imgobject[z].getBufferedImage().getWidth(),
                    imgobject[z].getBufferedImage().getHeight(), null);
        }
        return tag;
    }

    public static BufferedImage createSolidColor (int width, int height, String c) {
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = tag.createGraphics();
        Color color = parseToColor(c);
        g2d.setColor(color);
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();
        return tag;
    }

    public static BufferedImage createSolidColor (int width, int height, int rgb) {
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = tag.createGraphics();
        g2d.setColor(new Color(rgb));
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();
        return tag;
    }

    /**
     * 白色透明化
     *
     * @param bufferedImage
     */
    public static BufferedImage convert (BufferedImage bufferedImage) {
        // Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
        for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
            for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
                int rgb = filterRGB(bufferedImage.getRGB(j2, j1), Color.white.getRGB());
                bufferedImage.setRGB(j2, j1, rgb);
            }
        }
        return bufferedImage;
    }

    /**
     * 指定区域透明化
     *
     * @param bufferedImage
     */
    public static void convert (BufferedImage bufferedImage, int x, int y, int width, int height) {
        int argb = alphaRGB(0, 0);
        System.out.println("x:" + x);
        System.out.println("y:" + y);
        System.out.println("height:" + height);
        System.out.println("width:" + width);
        for (int i = x; i < (width + x); i++) {
            for (int j = y; j < (height + y); j++) {
                bufferedImage.setRGB(i, j, argb);
            }
        }
    }

    /**
     * 指定图像大小
     *
     * @param oldimg
     * @param width
     * @param height
     */
    public static BufferedImage zoomImage (String oldimg, int width, int height) {
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Image img = null;
        try {
            File file = new File(oldimg);
            img = ImageIO.read(file);
            Graphics2D g2d = tag.createGraphics();
            //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //g2d.setRenderingHints(getMyRenderingHints());
            g2d.drawImage(img, 0, 0, width, height, null); // 绘制后的图
        } catch (IOException e) {
            System.out.println("处理文件出现异常");
        }
        return tag;
    }

    public static BufferedImage makeRoundedCorner (BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return output;
    }

    /**
     * 判断是否为rgb2，是就计算出透明值，不是就原样返回
     *
     * @param rgb  要判断的颜色
     * @param rgb2 指定要透明的颜色
     * @return
     */
    public static int filterRGB (int rgb, int rgb2) {
        DirectColorModel dcm = (DirectColorModel) ColorModel.getRGBdefault();
        // DirectColorModel类用来将ARGB值独立分解出来
        int red = dcm.getRed(rgb);
        int green = dcm.getGreen(rgb);
        int blue = dcm.getBlue(rgb);
        int alp = dcm.getAlpha(rgb);

        int red2 = dcm.getRed(rgb2);
        int green2 = dcm.getRed(rgb2);
        int blue2 = dcm.getRed(rgb2);
        int alpha = 0;
        // 如果像素为指定颜色，则让它透明
        if (red == red2 && blue == green2 && green == blue2) {
            alpha = 0;
        } else {
            alpha = 255;
        }
        // png和gif格式图片透明部分仍然透明
        if (alp == 0) {
            alpha = 0;
        }
        return (alpha << 24) | (rgb & 0x00ffffff);// 进行标准ARGB输出以实现图象过滤
    }

	/*
     * public static BufferedImage convert(BufferedImage bi) { int width =
	 * bi.getWidth(); int height = bi.getHeight(); BufferedImage bufferedImage =
	 * new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR); int minX
	 * = bufferedImage.getMinX(); int minY = bufferedImage.getMinY(); int alpha
	 * = 0; for (int i = minY; i < height; i++) { for (int j = minX; j < width;
	 * j++) { int rgb = bi.getRGB(i, j); if (colorInRange(rgb)) alpha = 0; else
	 * alpha = 255; rgb = (alpha << 24) | (rgb & 0x00ffffff);
	 * bufferedImage.setRGB(i, j, rgb); } } return bufferedImage; }
	 */

    public static void writeImageIO (BufferedImage bi, String path) {
        File file = new File(path);
        try {
            ImageIO.write(bi, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 算法选择
     *
     * @return RenderingHints的一个对象
     */
    public static RenderingHints getMyRenderingHints () {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,// 抗锯齿提示键。
                RenderingHints.VALUE_ANTIALIAS_ON);// 抗锯齿提示值——使用抗锯齿模式完成呈现。
        rh.put(RenderingHints.KEY_TEXT_ANTIALIASING,// 文本抗锯齿提示键。
                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB);// 要求针对 LCD
        // 显示器优化文本显示
        rh.put(RenderingHints.KEY_ALPHA_INTERPOLATION,// Alpha 插值提示值
                RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);// Alpha
        // 插值提示值——选择偏重于精确度和视觉质量的
        // alpha
        // 混合算法
        rh.put(RenderingHints.KEY_RENDERING,// 呈现提示键。
                RenderingHints.VALUE_RENDER_QUALITY);// 呈现提示值——选择偏重输出质量的呈现算法
        rh.put(RenderingHints.KEY_STROKE_CONTROL,// 笔划规范化控制提示键。
                RenderingHints.VALUE_STROKE_NORMALIZE);// 几何形状应当规范化，以提高均匀性或直线间隔和整体美观。
        rh.put(RenderingHints.KEY_COLOR_RENDERING,// 颜色呈现提示键。
                RenderingHints.VALUE_COLOR_RENDER_QUALITY);// 用最高的精确度和视觉质量执行颜色转换计算。
        return rh;
    }

    public static boolean colorInRange (int color) {
        int red = (color & 0xff0000) >> 16;
        int green = (color & 0x00ff00) >> 8;
        int blue = (color & 0x0000ff);
        if (red >= 210 && green >= 210 && blue >= 210)
            return true;
        return false;
    }

    // 取色的
    public static int[] getRGB (BufferedImage image, int x, int y) {
        int[] rgb = new int[3];
        int pixel = image.getRGB(x, y);
        rgb[0] = (pixel & 0xff0000) >> 16;
        rgb[1] = (pixel & 0xff00) >> 8;
        rgb[2] = (pixel & 0xff);
        return rgb;
    }

    public static Color parseToColor (final String c) {
        Color convertedColor = Color.ORANGE;
        try {
            convertedColor = new Color(Integer.parseInt(c, 16));
        } catch (NumberFormatException e) {
            // codes to deal with this exception
        }
        return convertedColor;
    }

    public static int[] getARGB (BufferedImage image, int x, int y) {
        int pixel = image.getRGB(x, y);
        return getARGB(pixel);
    }

    /**
     * 图片 img 转为 bufferImage
     *
     * @param img
     * @return
     */
    public static BufferedImage toBufferedImage (Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    /**
     * 将指定颜色执行指定的透明化后返回
     *
     * @param rgb
     * @param alpha
     * @return
     */
    public static int alphaRGB (int rgb, int alpha) {
        return (alpha << 24) | (rgb & 0x00ffffff);// 进行标准ARGB输出以实现图象过滤
    }

    public static void atXYwriteImg (int x, int y, BufferedImage src, BufferedImage tar) {
        Graphics2D g2d = src.createGraphics();
        g2d.drawImage(tar, null, x, y);
    }

    public static int[] getARGB (int rgb) {
        DirectColorModel dcm = (DirectColorModel) ColorModel.getRGBdefault();
        // DirectColorModel类用来将ARGB值独立分解出来
        int red = dcm.getRed(rgb);
        int green = dcm.getGreen(rgb);
        int blue = dcm.getBlue(rgb);
        int alp = dcm.getAlpha(rgb);
        int[] rgba = new int[4];
        rgba[0] = red;
        rgba[1] = green;
        rgba[2] = blue;
        rgba[3] = alp;
        return rgba;
    }

    public static class BufferedImages {

        private int x;
        private int y;
        private BufferedImage bufferedImage;

        public BufferedImages (int x, int y, BufferedImage bufferedImage) {
            super();
            this.x = x;
            this.y = y;
            this.bufferedImage = bufferedImage;
        }


        public BufferedImages () {
            super();
        }


        public int getX () {
            return x;
        }

        public void setX (int x) {
            this.x = x;
        }

        public int getY () {
            return y;
        }

        public void setY (int y) {
            this.y = y;
        }

        public BufferedImage getBufferedImage () {
            return bufferedImage;
        }

        public void setBufferedImage (BufferedImage bufferedImage) {
            this.bufferedImage = bufferedImage;
        }


    }


}
