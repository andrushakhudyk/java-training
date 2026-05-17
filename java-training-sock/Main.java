import java.util.List;
import java.util.stream.Collectors;

public class Main{
    public static void main(String[] args){
        List<String> socks = List.of("black", "black", "red");

        String sock = socks.stream().filter(x -> x.equals("red")).collect(Collectors.joining());
        System.out.println(sock);
    }
}