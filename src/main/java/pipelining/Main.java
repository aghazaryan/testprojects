package pipelining;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by aghasighazaryan on 7/15/15.
 */
public class Main {


    public static void main(String[] args) {


        if(args.length != 3){
            System.out.println("please execute this with 3 arguments (batch size, host, port)");
            System.exit(1);
        }
        First first = new First(new Integer(args[0]),args[1],new Integer(args[2]));

        String pattern;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (!(pattern = br.readLine()).equals("")) {
                first.delete(pattern);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            first.disconnect();
        }


    }

}
