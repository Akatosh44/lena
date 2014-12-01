package lena;

import java.util.ArrayList;


/**
 * 
 * @author adumas37
 *
 */
public class Histogram {

	private Image histogram;
	private Image image;
	
	private int HEIGHT = 100;
	private int WIDTH = 255;
	
	/**
	 * Generator
	 */
	public Histogram(){
		this.histogram = new Image("histogram",WIDTH,HEIGHT);
		this.image = new Image();
	}
	
	/**
	 * Generator with an Image
	 * @param image
	 */
	public Histogram(Image image){
		this.histogram = new Image(image.getName()+"_histogram",WIDTH,HEIGHT);
		this.image = new Image(image);
	}
	
	/**
	 * Generate the histogram
	 */
	public void generate(){
		int[] levels = new int[256];
		int max = 0;
		ArrayList<Pixel> pixelList = new ArrayList<Pixel>();
		
		//Getting the number of pixels for each level
		for (Pixel px : image.getPixels()){
			//We add 1 to the level of the current pixel
			levels[px.getLevel()] ++;
		}
		
		//Getting the max
		for (int i=0; i<255; i++){
			
			if (levels[i]>max){max=levels[i];}
		}
		
		//putting in percent
		for (int i=0; i<255; i++){
			levels[i]=((int)((double)levels[i]*100.0/(double)max));
			System.out.println(levels[i]);
		}
		
		//computing
		for (int i=0; i<HEIGHT; i++){
			for (int j=0; j<WIDTH; j++){
				if (levels[j] <= 100-i){
					pixelList.add(new Pixel(j,i,255));
				}
				else {
					pixelList.add(new Pixel(j,i,0));
				}
			}
		}
		//Setting the histogram pixels
		this.histogram.setPixels(pixelList);
		
	}
	
	/**
	 * Getter histogram
	 * @return
	 */
	public Image getHistogram(){
		return this.histogram;
	}
	
	
}
