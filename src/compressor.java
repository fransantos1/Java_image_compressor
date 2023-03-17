
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;

import java.io.FileWriter;
import java.io.IOException;
import java.security.PrivilegedActionException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class compressor {

    private BufferedImage imagem;

    public compressor(String urlOriginalImage) {

        try {
            imagem = ImageIO.read(new File(urlOriginalImage));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private int SmallestCommonDiviser(int n1, int n2) {
       /*for(int i =2; i<10;i++){
        if( n1%i == 0 && n2 % i == 0){
            return i;
        }
       }
       System.out.println("couldnt find");
        System.exit(1);
        return 1;*/
        if (n2 == 0) {
            return n1;
        }
        return SmallestCommonDiviser(n2, n1 % n2);
    }
    public void Compress(String urlcompressedsave, String urlControlsave,int divider){
        int imageWidth = imagem.getWidth();
        int imageHeight = imagem.getHeight();

        int gcd = divider;
        //SmallestCommonDiviser(imageWidth, imageHeight);

        int compressedWidth = imageWidth/gcd;
        int compressedHeight = imageHeight/gcd;

        int section_n = gcd*gcd;

        Color cor, corsave, Controlcor;

        System.out.println(gcd);

        int pointer_x=0, pointer_y=0;
        BufferedImage Compressed = new BufferedImage(compressedWidth, compressedHeight, BufferedImage.TYPE_INT_RGB);
        BufferedImage Control = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < imageHeight; i++) {
                for (int j = 0; j < imageWidth; j++) { 
                    Controlcor = new Color(imagem.getRGB(j,i));
                    Control.setRGB(j,i, Controlcor.getRGB());  
                }
            }




        for (int i = 0; i < compressedHeight; i++) {
            for (int j = 0; j < compressedWidth; j++) { 
                int r=0,g=0,b=0;

                for(int y = 0; y<gcd;y++){
                    for(int x = 0; x<gcd;x++ ){
                        cor = new Color(imagem.getRGB(pointer_x +x, pointer_y+y));
                        r= r+cor.getRed();
                        g= g+cor.getGreen();
                        b=b+cor.getBlue();
                    }
                }
                r = r/section_n;
                g = g/section_n;
                b = b/section_n;

                corsave = new Color(r, g, b);
                Compressed.setRGB(j,i, corsave.getRGB());

              
                pointer_x = pointer_x+ gcd;

            }
            pointer_x = 0;
            pointer_y = pointer_y+gcd;
        }
        try {
            ImageIO.write(Compressed, "jpg", new File(urlcompressedsave));
            ImageIO.write(Control, "jpg", new File(urlControlsave));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void CompressAndBinary(String fileurl){
        int imageWidth = imagem.getWidth();
        int imageHeight = imagem.getHeight();
        Color color;
        try {
            FileWriter binary = new FileWriter(fileurl);
            for (int i = 0; i < imageHeight; i++) {
                for (int j = 0; j < imageWidth; j++) { 
                    color = new Color(imagem.getRGB(j,i));
                    int r = color.getRed();
                    int g = color.getGreen();
                    int b = color.getBlue();
                    while(r%5 != 0){
                        r++;
                    }
                    while(g%5 != 0){
                        g++;
                    }
                    while(b%5 != 0){
                        b++;
                    }
                    r = r/5;
                    g= g/5;
                    b = b/5; 
                    binary.write(Integer.toBinaryString(r)+""+Integer.toBinaryString(g)+""+Integer.toBinaryString(b));
                    r = 0;
                    g = 0;
                    b = 0;
                }
                    





                
            }
            binary.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





public void CompressMaxValue(String urlcompressedsave, String urlControlsave,int divider){
        int imageWidth = imagem.getWidth();
        int imageHeight = imagem.getHeight();

        int gcd = divider;
        //SmallestCommonDiviser(imageWidth, imageHeight);

        int compressedWidth = imageWidth/gcd;
        int compressedHeight = imageHeight/gcd;

        int section_n = gcd*gcd;

        Color cor, corsave, Controlcor;

        System.out.println(gcd);

        int pointer_x=0, pointer_y=0;
        BufferedImage compressedlow = new BufferedImage(compressedWidth, compressedHeight, BufferedImage.TYPE_INT_RGB);
        BufferedImage Compressedhigh = new BufferedImage(compressedWidth, compressedHeight, BufferedImage.TYPE_INT_RGB);
        BufferedImage Compressed = new BufferedImage(compressedWidth, compressedHeight, BufferedImage.TYPE_INT_RGB);
        BufferedImage Control = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);


        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) { 
                Controlcor = new Color(imagem.getRGB(j,i));
                Control.setRGB(j,i, Controlcor.getRGB());
            }
        }
        for (int i = 0; i < compressedHeight; i++) {
            for (int j = 0; j < compressedWidth; j++) { 
                int r=-1,g=-1,b=-1;
                for(int y = 0; y<gcd;y++){
                    for(int x = 0; x<gcd;x++ ){
                        cor = new Color(imagem.getRGB(pointer_x +x, pointer_y+y));
                        if(r<cor.getRed()){
                           r= cor.getRed();
                        }
                        if(g<cor.getGreen()){
                            g= cor.getGreen();
                        }
                        if(g<cor.getBlue()){
                            b=cor.getBlue();
                        }
                    }
                }
                r = r/section_n;
                g = g/section_n;
                b = b/section_n;
                corsave = new Color(r, g, b);
                compressedlow.setRGB(j,i, corsave.getRGB());
                pointer_x = pointer_x+ gcd;
            }
            pointer_x = 0;
            pointer_y = pointer_y+gcd;
        }
        pointer_y = 0;
        for (int i = 0; i < compressedHeight; i++) {
            for (int j = 0; j < compressedWidth; j++) { 
                int r=257,g=257,b=257;
                for(int y = 0; y<gcd;y++){
                    for(int x = 0; x<gcd;x++ ){
                        cor = new Color(imagem.getRGB(pointer_x +x, pointer_y+y));
                        if(r>cor.getRed()){
                           r= cor.getRed();
                        }
                        if(g>cor.getGreen()){
                            g= cor.getGreen();
                        }
                        if(g>cor.getBlue()){
                            b=cor.getBlue();
                        }
                    }
                }
                r = r/section_n;
                g = g/section_n;
                b = b/section_n;
                corsave = new Color(r, g, b);
                Compressedhigh.setRGB(j,i, corsave.getRGB());
                pointer_x = pointer_x+ gcd;
            }
            pointer_x = 0;
            pointer_y = pointer_y+gcd;
        }
        
        for (int i = 0; i < compressedHeight; i++) {
            for (int j = 0; j < compressedWidth; j++) { 
                Color cor1 = new Color(Compressedhigh.getRGB(j,i));
                Color cor2 = new Color(compressedlow.getRGB(j,i));
                int r,r1,g,g1,b,b1;
                r = cor1.getRed();
                r1 =cor2.getRed();
                g = cor1.getGreen();
                g1= cor2.getGreen();
                b = cor1.getBlue();
                b1 = cor2.getBlue();
                Color cor3 = new Color((r+r1)/2, (g+g1)/2, (b+b1)/2);
                Compressed.setRGB(j, i, cor3.getRGB());
            }
        }
        try {
            ImageIO.write(Compressed, "jpg", new File(urlcompressedsave));
            ImageIO.write(Control, "jpg", new File(urlControlsave));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
