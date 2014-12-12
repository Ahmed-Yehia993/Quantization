package Quantization;

import java.awt.Color;
import java.awt.TextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class quantization {
	static int sum = 0;

	public static int getRed(int x) {
		return (x >> 16 & 255);
	}

	public static int getGreen(int x) {
		return (x >> 8 & 255);
	}

	public static int getBlue(int x) {
		return (x & 255);
	}

	public static int getStep(int levels) {

		return 256 / levels;
	}

	public static void compress(String path, String newpath, TextField lev) {
		int width, hight;
		int level;
		// System.out.println(level);

		level = Integer.parseInt(lev.getText());
		int step;
		step = quantization.getStep(level);

		BufferedImage pic = null;
		try {
			pic = ImageIO.read(new File(path));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		width = pic.getWidth();
		hight = pic.getHeight();

		BufferedImage newpic = new BufferedImage(width, hight, pic.getType());
		for (int i = 0; i < pic.getWidth(); i++) {
			for (int j = 0; j < pic.getHeight(); j++) {
				int x1 = pic.getRGB(i, j);
				int out = new Color((((quantization.getRed(x1) / step))),
						(((quantization.getGreen(x1) / step))),
						(((quantization.getBlue(x1) / step)))).getRGB();
				newpic.setRGB(i, j, out);

				int s = x1 - out;

				sum += Math.pow(s, 2);
			}
		}
		System.out.println(" mean square error = " + sum / (width * hight));
		File newpi = new File(newpath);
		try {
			ImageIO.write(newpic, "jpg", newpi);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static void Decompress(String path, String newpath, TextField lev) {
		int width, hight;
		int level;
		// System.out.println(level);

		level = Integer.parseInt(lev.getText());
		int step;
		step = quantization.getStep(level);

		BufferedImage pic = null;
		try {
			pic = ImageIO.read(new File(path));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		width = pic.getWidth();
		hight = pic.getHeight();

		BufferedImage newpic = new BufferedImage(width, hight, pic.getType());

		for (int i = 0; i < pic.getWidth(); i++) {
			for (int j = 0; j < pic.getHeight(); j++) {
				int x1 = pic.getRGB(i, j);
				int red = (((quantization.getRed(x1) * step) + (step / 2)));
				int green = (((quantization.getGreen(x1) * step) + (step / 2)));
				int blue = (((quantization.getBlue(x1) * step) + (step / 2)));

				if (red > 255)
					red = 255;
				if (green > 255)
					green = 255;
				if (blue > 255)
					blue = 255;
				int out = new Color(red, green, blue).getRGB();
				newpic.setRGB(i, j, out);
			}

		}

		File newpi = new File(newpath);
		try {
			ImageIO.write(newpic, "jpg", newpi);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new App();
	}

}
