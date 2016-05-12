import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
public class ImageMatrixConverter {
	private static String line="0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	182	164	37	5	0	3	53	180	91	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	3	99	221	254	254	183	53	177	254	254	254	18	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	22	162	254	254	254	254	254	254	254	254	254	255	18	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	50	254	254	254	254	254	247	250	254	254	254	119	8	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	162	254	254	254	247	128	107	204	223	162	223	21	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	162	254	254	219	70	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	162	254	254	198	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	5	127	225	254	254	237	174	141	50	10	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	25	254	254	254	254	254	254	254	254	195	31	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	25	254	254	254	254	254	254	254	254	254	198	58	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	7	179	254	254	254	239	203	254	254	254	254	161	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	111	179	158	55	43	14	149	202	254	254	234	24	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	75	254	254	254	131	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	43	0	0	0	0	0	0	0	118	254	254	254	113	0	0	0	0	0	0	0	0	0	0	0	0	0	0	10	197	142	35	0	0	0	0	72	220	254	254	220	19	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	101	254	217	83	0	0	25	217	254	254	254	161	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	3	165	254	250	145	167	203	254	254	254	254	161	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	88	214	254	254	254	254	254	254	254	216	29	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	26	214	254	254	254	254	254	158	29	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	25	154	227	221	254	121	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0";
	private static ArrayList<Integer> tupl=new ArrayList<Integer>();
	public static ArrayList<Integer> converterI2M(String input, boolean whiteBackGround){
		ArrayList<Integer> tupl=new ArrayList<Integer>();
		File file = new File(input);  
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
	        	int r = (pixel & 0xff0000) >> 16;  
				int g = (pixel & 0xff00) >> 8;  
	        	int b = (pixel & 0xff); 
	        	if(whiteBackGround) tupl.add((r+g+b)/3);
	        	else{
	        		tupl.add(255-(r+g+b)/3);
	        	}
	         }  
	     }
	    for(Integer i:tupl)
	    	System.out.println(i+"  ");
		return tupl;
	}
	public static void converterM2I(String line,String output){
		BufferedImage dest = new BufferedImage(28,28,BufferedImage.TYPE_3BYTE_BGR);
	    
		String[] ss=line.split("\\s+");
		for(int i=0;i<28;i++){
			for(int j=0;j<28;j++){
				int r=Integer.parseInt(ss[i*28+j]);
				int g=Integer.parseInt(ss[i*28+j]);
				int b=Integer.parseInt(ss[i*28+j]);
				int pixel = (255 << 24) | (r << 16) | (g << 8) | b;
				dest.setRGB(i, j, pixel);
			}
		}
	    try {
				ImageIO.write(dest, "jpg", new File(output));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		tupl=converterI2M("image3.jpg", false);
		converterM2I(line,"output3.jpg");
	}
}
