
public class hello{
    public static void main(String[] args) throws InterruptedException{

        for(int i= 0; i<10; i++){
            System.out.println(i);
            Thread.sleep(1000);
        }
        System.out.println("Happy Birthday");
    }
}