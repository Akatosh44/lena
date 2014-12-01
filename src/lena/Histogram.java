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
	
	
	public Histogram(){
		this.histogram = new Image();
		this.image = new Image();
	}
	
	public Histogram(Image image){
		this.histogram = new Image(image.getName(),WIDTH,HEIGHT);
		this.image = new Image(image);
	}
	
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
		for (int nb : levels){
			if (nb>max){max=nb;}
		}
		
		//putting in percent
		for (int i=0; i<255; i++){
			levels[i]=levels[i]/max*100;
		}
		
		//computing
		for (int i=0; i<HEIGHT; i++){
			for (int j=0; j<WIDTH; j++){
				if (levels[j] <= i){
					pixelList.add(new Pixel(j,i,255));
				}
				else {
					pixelList.add(new Pixel(j,i,0));
				}
			}
		}
		
	}
}
