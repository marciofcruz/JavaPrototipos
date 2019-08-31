package br.com.aps.biometria.tipos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Imagem {

	private int[][] frequenciaRGB;
	private int[][] freqYUV;
	private int[][] freqHSV;

	public Imagem(String arquivoNoDisco) throws IOException {

		BufferedImage imagem = ImageIO.read(new File(arquivoNoDisco));

		this.frequenciaRGB = new int[256][3];
		this.freqHSV = new int[imagem.getWidth() * imagem.getHeight()][3];
		this.freqYUV = new int[imagem.getWidth() * imagem.getHeight()][3];
		this.calculaFrequencia(imagem);

		imagem = null;
	}

	public int[][] getFrequencia() {
		return this.frequenciaRGB;
	}

	private void calculaFrequencia(BufferedImage imagem) {

		int indice = 0;

		for (int j = 0; j < imagem.getWidth(); j++)
			for (int k = 0; k < imagem.getHeight(); k++, indice++) {
				Color cor = new Color(imagem.getRGB(j, k));

				int r = cor.getRed();
				int g = cor.getGreen();
				int b = cor.getBlue();

				this.frequenciaRGB[r][0] += 1;
				this.frequenciaRGB[g][1] += 1;
				this.frequenciaRGB[b][2] += 1;

				this.rgbParaHsv(r, g, b, freqHSV[indice]);
				this.rgbParaYuv(r, g, b, this.freqYUV[indice]);

			}// fim do for

	}// fim do calculaFrequencia

	/*
	 * Calcula frequencia dos 256 tons de cinza
	 */
	private void rgbParaYuv(int r, int g, int b, int yuv[]) {

		int y = (int) (0.299 * r + 0.587 * g + 0.114 * b);
		int u = (int) ((b - y) * 0.492f);
		int v = (int) ((r - y) * 0.877f);

		yuv[0] = y;
		yuv[1] = u;
		yuv[2] = v;
	}

	private void rgbParaHsv(int r, int g, int b, int hsv[]) {

		int min, max, delMax;

		if (r > g) {
			min = g;
			max = r;
		} else {
			min = r;
			max = g;
		}
		if (b > max)
			max = b;
		if (b < min)
			min = b;

		delMax = max - min;

		float H = 0, S;
		float V = max;

		if (delMax == 0) {
			H = 0;
			S = 0;
		} else {
			S = delMax / 255f;
			if (r == max)
				H = ((g - b) / (float) delMax) * 60;
			else if (g == max)
				H = (2 + (b - r) / (float) delMax) * 60;
			else if (b == max)
				H = (4 + (r - g) / (float) delMax) * 60;
		}

		hsv[0] = (int) (H);
		hsv[1] = (int) (S * 100);
		hsv[2] = (int) (V * 100);
	}

	@Override
	public String toString() {
		return "Imagem [frequenciaRGB=" + Arrays.toString(frequenciaRGB) + "]";
	}

}
