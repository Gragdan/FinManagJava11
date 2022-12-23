
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) throws IOException {
        Calculations calculations = new Calculations();
        File tsvFile = new File("categories.tsv");
        ReaderTsv readerTsv = new ReaderTsv();
        HashMap<String, String> categoryItems = new HashMap<>();

        System.out.println("Server run");
        try (ServerSocket serverSocket = new ServerSocket(8989);) { // стартуем сервер один(!) раз
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    out.println("hi, bro! I am server :)");
                    categoryItems = readerTsv.run(tsvFile);
                    out.flush();
                    String fromClient = in.readLine();
                    System.out.println(fromClient);

                    try {
                        JSONObject jsonObject = (JSONObject) new JSONParser().parse(fromClient);

                        System.out.println(calculations.parseData((String) jsonObject.get("date")));


                        out.println((calculations.maxCategory((String)
                                jsonObject.get("title"), (Long) jsonObject.get("sum"), categoryItems)));


                    } catch (NullPointerException ex) {
                        ex.printStackTrace();
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    } catch (java.text.ParseException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}