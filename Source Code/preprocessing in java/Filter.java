import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
	
public class Filter {
	private static void filer(String name,String output){
		File file = new File(name);  
	    BufferedImage image = null;  
	    try{  
	        image = ImageIO.read(file);  
	    }catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	    int width = image.getWidth();  
	    int height =image.getHeight(); 
	    BufferedImage dest = new BufferedImage(width-1,height-1,BufferedImage.TYPE_3BYTE_BGR);
	    for (int i = 1; i < width-1; i++) {  
	        for (int j = 1; j < height-1; j++) {
	        	int[] r=new int[9];
	        	int[] g=new int[9];
	        	int[] b=new int[9];
	        	for(int k=0;k<9;k++){
	        		r[k]=0;
	        		g[k]=0;
	        		b[k]=0;
	        	}
	        	int index=0;
	        	for(int x=i-1;x<=i+1;x++){
	        		for(int y=j-1;y<=j+1;y++){
	        			int pixel=image.getRGB(x, y);
	        			int br = (pixel & 0xff0000) >> 16;  
	                	int bg = (pixel & 0xff00) >> 8;  
	                	int bb = (pixel & 0xff);
	                	r[index]=br;
	                	g[index]=bg;
	                	b[index]=bb;
	                	index++;
	        		}
	        	}
	        	int sumR=0;
	        	int sumG=0;
	        	int sumB=0;
	        	for(int k=0;k<9;k++){
	        		sumR+=r[k];
	        		sumG+=g[k];
	        		sumB+=b[k];
	        	}
	        	int newPixel = (255 << 24) | (sumR/9 << 16) | (sumG/9 << 8) | sumB/9;
	        	
	        	dest.setRGB(i, j, newPixel);
	         }  
	        try {
				ImageIO.write(dest, "jpg", new File(output));
			} catch (IOException e) {
				e.printStackTrace();
			}
	     }
	}
	public static void main(String[] args) {
		String input=args[0];
		String output=args[1];
		filer(input,output);
	}

}
