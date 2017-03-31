import java.awt.Graphics2D;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.*;
import javax.imageio.ImageIO;

public class Ahash{

		private static int size_m = 8;
		private static int size_n = 8;

		static BufferedImage resize(String inputImagePath, int scaledWidth, int scaledHeight) throws IOException {
    
            File inputFile = new File(inputImagePath);
            BufferedImage inputImage = ImageIO.read(inputFile);
 
            BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());
 
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
            g2d.dispose();
 
            return outputImage;
        }

		static BufferedImage togray(BufferedImage img){

            for(int i=0;i<size_m;i++){
                for(int j=0;j<size_n;j++){
                    int pixel = img.getRGB(j,i);
                    Color c = new Color(pixel);
                    int r = (int)(c.getRed()*0.299);
                    int b = (int)(c.getBlue()*0.587);
                    int g = (int)(c.getGreen()*0.114);
                    Color cg = new Color(r+b+g,g+r+b,b+r+g);
                    img.setRGB(j,i,cg.getRGB());
                }
            }
            return img;
        }

        public int distance(String s1, String s2) {
                int counter = 0;
                for (int k = 0; k < s1.length();k++) {
                        if(s1.charAt(k) != s2.charAt(k)) {
                                counter++;
                        }
                }
                return counter;
        }

		public String getHash(String input) throws Exception{

			File file = new File(input);
			BufferedImage img = ImageIO.read(file);

			String hash="";

			img = resize(input,size_n,size_m);
			img = togray(img);

			int[][]color_mat = new int [size_m][size_n];
			int sum=0;
			double mean=0.0;

			for(int i=0;i<size_m;i++){
				for(int j=0;j<size_n;j++){
					int pixel = img.getRGB(j,i);
					Color c = new Color(pixel);
					int r = (int)c.getRed();
					int g = (int)c.getGreen();
					int b = (int)c.getBlue();

					color_mat[i][j] = r;
					sum+=r;
				}
			}

			mean = sum/(size_m*size_n);


			for(int i=0;i<size_m;i++){
				for(int j=0;j<size_n-1;j++)
					if(color_mat[i][j]<mean)
						hash+="0";
					else
						hash+="1";
			}

			return hash;
		}

};