package com.company;


import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.LinkedList;


public class Crawler {

    void searchSites(String host, int maxDepth) throws IOException {
        final String URL_PREFIX = "https://";

        LinkedList<URLDepthPair> viewedURL = new LinkedList<>();
        LinkedList<URLDepthPair> notViewedURL = new LinkedList<>();

        String host1 = URL_PREFIX + host;
        notViewedURL.add(new URLDepthPair(host1, 0));

        Socket soc = new Socket(host, 80);

        while (!notViewedURL.isEmpty()) {
            host1 = notViewedURL.getFirst().getUrl();
            URL url = new URL(host1);
            try {

                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

                String line = reader.readLine();
                while (line != null) {
                    for (String s : line.split("href=\"")) {
                        if (s.startsWith(URL_PREFIX)) {
                            s = s.substring(s.indexOf(URL_PREFIX));
                            try {
                                s = s.substring(0, s.indexOf('"'));
                            } catch (Exception err) {
                            }

                            if (viewedURL.contains(new URLDepthPair(s)) || notViewedURL.contains(new URLDepthPair(s)))
                                continue;

                            else {
                                if (new URLDepthPair(s, notViewedURL.get(0).getDepth() + 1).getDepth() < maxDepth)
                                    notViewedURL.add(new URLDepthPair(s, notViewedURL.getFirst().getDepth() + 1));
                            }
                        }
                    }
                    line = reader.readLine();
                }
                soc.close();
                viewedURL.add(notViewedURL.getFirst());
                notViewedURL.removeFirst();
                System.out.println(viewedURL.getLast().toString());
                reader.close();
            } catch (IOException error) {
                System.out.println(error.getMessage());
            }
        }

        System.out.println("Просканировано " + viewedURL.size() + " ссылок");
    }
}
