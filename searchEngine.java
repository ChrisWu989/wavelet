import java.io.IOException;
import java.net.URI;
import java.util.*;

class Handler implements URLHandler {
    List<String> str = new ArrayList<String>();
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("String: %s", str);
        }

        else if (url.getPath().equals("/add")) {
            String[] parameters = url.getQuery().split("=");
            str.add(parameters[1]);
            return String.format("String %s added!", parameters[1]);
        } else {
            // System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/search")) {
                List<String> str2 = new ArrayList<String>();
                String[] parameters = url.getQuery().split("=");
                for (String s : str) {
                    if (s.contains(parameters[1])) {
                        str2.add(s);
                    }
                }
                String comma = ",";
                String finalStr = String.join(comma, str2);
                return finalStr;
            }
            return "404 Not Found!";
        }
    }
}

class searchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}