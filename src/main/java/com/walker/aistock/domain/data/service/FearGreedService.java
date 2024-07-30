package com.walker.aistock.domain.data.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.walker.aistock.domain.common.enums.FinancialMetrics.*;
import static com.walker.aistock.domain.common.enums.Url.FINVIZ_STOCK_DETAIL;

@Service
public class FinvizService {

    public String scrapingFinviz(String ticker) throws IOException {

        Document doc = Jsoup.connect(FINVIZ_STOCK_DETAIL.getUrl() + ticker).get();

        // TODO td 약 1000개 > data-boxover 약 90개 > 8개 요소의 형제 값 추출 : 효율적인 알고리즘 고안
        // 모든 <td> 요소를 선택하여 순회
        Elements tdElements = doc.select("td");

        // TODO PER 외의 다른 요소들도 추출
        for (Element td : tdElements) {
            // "Price-to-Earnings (ttm)" 텍스트를 포함하는 <td> 요소를 찾음
            if (td.attr("data-boxover").contains(PER.getAttr())) {
                // 다음 형제 <td> 요소를 찾음
                Element valueElement = td.nextElementSibling();

                if (valueElement != null) {
                    // <b> 요소 내의 텍스트 값을 추출
                    return valueElement.select("b").text();
                    // 값 출력
                } else {
                    System.out.println("다음 형제 <td> 요소를 찾을 수 없습니다.");
                }
            }
        }

        return "None";
    }

}
