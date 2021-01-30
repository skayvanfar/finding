package ir.sk.query.crawler;


import ir.sk.query.model.Link;
import ir.sk.query.model.Query;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/29/2021.
 */
public class GoogleCrawler implements SearchEngineCrawler {

    private final static String SEARCH_ENGINE = "https://www.google.com/search?q=";
    private final static int RESULT_COUNT = 11;

    private String userAgent = "Mozilla/5.0 (compatible; Googlebot/2.1; http://www.google.com/bot.html)";

    public GoogleCrawler() {
    }

    public GoogleCrawler(String userAgent) {
        this.userAgent = userAgent;
    }

    private String getString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while ((line = br.readLine()) != null)
                sb.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * The method will return the search page result
     */
    private String getSearchContent(String googleSearchQuery) throws Exception {
        URL url = new URL(googleSearchQuery);
        final URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", userAgent);
        connection.setRequestProperty("Content-Language", "en-US");
        final InputStream stream = connection.getInputStream();
        return getString(stream);
    }

    /**
     * Parse all links
     */
    private Query parseLinks(final String html) throws Exception {
        Document doc = Jsoup.parse(html);
        Query query = new Query();
        Element didYouMean = doc.select("#scc").select("span").first();
        if (didYouMean != null)
            query.setSpelling(didYouMean.text());
        Elements results = doc.select("a > h3");

        results.stream().forEach(link -> {
            Elements parent = link.parent().getAllElements();
            String relHref = parent.attr("href");
            if (relHref.startsWith("/url?q="))
                relHref = relHref.replace("/url?q=", "");

            String[] splittedString = relHref.split("&sa=");
            if (splittedString.length > 1)
                relHref = splittedString[0];

            Link newLink = new Link(link.text(), relHref, link.parent().parent().parent().text());

            query.add(newLink);
        });
        return query;
    }

    @Override
    public Query search(String searchTerm, Integer resultCount) throws Exception {
        if (resultCount == null)
            resultCount = RESULT_COUNT;

        System.out.println("Searching for: " + searchTerm);

        searchTerm = URLEncoder.encode(searchTerm, StandardCharsets.UTF_8.toString());
        String url = new StringBuilder(SEARCH_ENGINE).append(searchTerm).append("&num=").append(resultCount).toString();

        String page = getSearchContent(url);
        return parseLinks(page);
    }

}
