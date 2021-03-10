package unittests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import renderer.ImageWriter;

public class ImageWriterTest {

	@Test
	public void  ImageWiterWriteToImageTest() {
        ImageWriter imageWriter = new ImageWriter("Beutiful picture1", 1600, 1000, 800, 500);
        int Nx = imageWriter.getNx();
        int Ny = imageWriter.getNy();
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                if (i % 50 == 0 || j % 50 == 0) {
                    imageWriter.writePixel(j, i, Color.pink);
                } else {
                    imageWriter.writePixel(j, i, Color.white);
                }
            }
        }
        imageWriter.writeToImage();
    }

}
