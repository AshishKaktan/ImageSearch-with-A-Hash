import java.util.*;

class Driver{

	public static void main(String [] ar){
		try{
           	Ahash ahash = new Ahash();
            int threshold = 15,bit_distance=0;
            String img1 = ahash.getHash("TestImage/firework.jpg");
            String img2 = ahash.getHash("TestImage/Kola_sharp.jpg");

            bit_distance = ahash.distance(img1,img2);
            System.out.println("Number of Different bits : "+bit_distance);
            if(bit_distance<threshold)
                System.out.println("Images might be similar with threshold : "+threshold+" bits");
            else
                System.out.println("Images might be different");

        }catch(Exception e){
            System.out.println(e);
            }
	}
};
