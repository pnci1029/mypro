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

        Elements select = document.select(".deal-name");

        System.out.println(select.text() + "\n");

    }
}
