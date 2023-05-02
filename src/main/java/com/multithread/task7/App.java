package com.multithread.task7;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.io.File;

public class App {

    public static final String TIFF = "tiff";

    public static void main(String[] args) {
        String fileName = "C:\\Users\\Yevgenii_Krasnenkov\\IdeaProjects\\mypic.jfif";
        String fileWritePixels = "C:\\Users\\Yevgenii_Krasnenkov\\IdeaProjects\\pic.tiff";
        int[] pixels;
        BufferedImage read;
        int[] dst;
        int width;
        int height;
        try {
            read = ImageIO.read(new File(fileName));
            width = read.getWidth();
            height = read.getHeight();
            pixels = read.getRGB(0, 0, width, height, null, 0, width);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dst = new int[width * height];
        blur(pixels, dst);
        write(fileWritePixels, width, height, dst);
    }

    private static void blur(int[] pixels, int[] dst) {
        ForkBlur fb = new ForkBlur(pixels, 0, pixels.length, dst);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.invoke(fb);
    }

    public static void write(String filename, int width, int height, int[] pixelData) {
        try {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            image.setRGB(0, 0, width, height, pixelData, 0, width);
            File file = new File(filename);
            ImageIO.write(image, TIFF, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
