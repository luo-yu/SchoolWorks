import java.util.Scanner;


public class Remote {

	protected int volume;
	protected int channel;
	
	protected final int MAXIMUM_VOLUME = 20;
	protected final int MAXIMUM_CHANNEL = 199;
	protected final int DEFAULT_CHANNEL = 2;
	protected final int DEFAULT_VOLUME = 5;
	protected final int MINIMUM_VOLUME = 0;
	protected final int MINIMUM_CHANNEL = 1;
	
	public Remote(){
		this.channel = DEFAULT_CHANNEL;
		this.volume = DEFAULT_VOLUME;
	}
	public void volumeUp(){
		if(this.volume<MAXIMUM_VOLUME)
			volume++;
	}
	public void volumeDown(){
		if(this.volume>MINIMUM_VOLUME)
			volume--;
	}
	public void channelUP(){
		if(this.channel<MAXIMUM_CHANNEL)
			channel++;
	}
	public void channelDown(){
		if(this.channel>MINIMUM_CHANNEL)
			channel--;
	}
	
	public void display(){
		System.out.println("\n--------------------");
		System.out.println("Channel: " + channel);
		System.out.println("Volume: " + volume);
		System.out.println("--------------------\n");
	}
	
	public void menu(){
		Scanner input = new Scanner(System.in);
		String choice;
		System.out.println("POWER ON");
		display();
		do{
			System.out.println("Channel up:    +" );
			System.out.println("Channel down:    -");
			System.out.println("Volume up:     ++");
			System.out.println("Volume down:    --");
			System.out.println("Power off:    o");
			System.out.print("Choose: ");
			choice = input.next();
			if(choice.equals("+"))
				channelUP();
			else if(choice.equals("-"))
				channelDown();
			else if(choice.equals("++"))
				volumeUp();
			else if(choice.equals("--"))
				volumeDown();
			display();
		}while(!choice.equals("o"));
		System.out.println("POWER OFF");
	}
	
	public static void main(String[] args){
		Remote remote = new Remote();
		remote.menu();
	}
}
