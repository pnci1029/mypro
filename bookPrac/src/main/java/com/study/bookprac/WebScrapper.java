package com.study.bookprac;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

@Slf4j
public class WebScrapper {
    public static void main(String[] args) throws IOException {
        String url = "https://www.test.com/deal/rank";

        Document document = Jsoup.connect(url).get();

        Elements select = document.select(".deal-title");
        System.out.println("select = " + select);
        for (Element element : select) {
            Elements value = select.select(".deal-name");
            System.out.println("value = " + value);
            System.out.println(element.select(".product-title").size());
        }

        Elements select2 = document.select("div.deal-title");
        Elements select3 = document.select("product-link ");
        System.out.println("select2 = " + select2.size());
        System.out.println("select3 = " + select3.size());

    }
}
