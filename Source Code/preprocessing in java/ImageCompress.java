import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class ImageCompress {
	private static ArrayList<Pi> pixels=new ArrayList<Pi>();
	private static ArrayList<Pi> read(String name){
		ArrayList<Pi> pixels=new ArrayList<Pi>();
		File file = new File(name);  
	    BufferedImage image = null;  
	    try{  
	        image = ImageIO.read(file);  
	    }catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	    int width = image.getWidth();  
	    int height =image.getHeight(); 
	    for (int i = 0; i < width; i++) {  
	        for (int j = 0; j < height; j++) {
	        	int pixel = image.getRGB(i, j);
	        	Pi p=new Pi(i,j,pixel);
	        	pixels.add(p);
	         }  
	     }
	    return pixels;
	}
	private static void write(String input, String output,ArrayList<Pi> pixels){
		File file = new File(input);  
	    BufferedImage image = null;  
	    try{  
	        image = ImageIO.read(file);  
	    }catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	    int width = image.getWidth();  
	    int height =image.getHeight(); 
	      BufferedImage dest = new BufferedImage(28,28,BufferedImage.TYPE_3BYTE_BGR);
	        System.out.println(pixels.size());
	        ArrayList<Pi> newPixels=new ArrayList<Pi>();
	        for(int i=0;i<28;i++){
	        	for (int j=0;j<28;j++){
	        		int x=(int)(i*width/28);
	        		int y=(int)(j*height/28);
	        		int index=pixels.indexOf(new Pi(x,y));
	        		int pixel=pixels.get(index).pixel;
	        		Pi p=new Pi(i,j,pixel);
	        		newPixels.add(p);
	        	}
	        }
	        for(Pi pp:newPixels){
	        	dest.setRGB(pp.i, pp.j, pp.pixel);
	        }
	        try {
				ImageIO.write(dest, "jpg", new File(output));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public static void main(String[] args) {
		String input = args[0];
		String output=args[1];
		pixels=read(input);
		write(input,output,pixels);
	}

}
