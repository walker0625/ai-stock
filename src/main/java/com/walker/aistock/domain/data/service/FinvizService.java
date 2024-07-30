package com.walker.aistock.domain.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FinvizService {

    public void crawlingFinviz() throws IOException {
        Document doc = Jsoup.connect("https://finviz.com/quote.ashx?t=NVDA&p=d").get();

        System.out.println(doc.toString());
    }

}
