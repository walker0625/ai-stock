package com.walker.aistock.backend.data.service;

import com.walker.aistock.backend.data.dto.res.FinvizDetailRes;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.walker.aistock.backend.common.enums.Indicator.*;
import static com.walker.aistock.backend.common.enums.Url.FINVIZ_DETAIL;

@Slf4j
@Service
public class FinvizService {

    public FinvizDetailRes scrapingFinviz(String ticker){

        FinvizDetailRes finvizDetailRes = new FinvizDetailRes();

        Document doc = null;
        try {
            doc = Jsoup.connect(String.format(FINVIZ_DETAIL.getValue(), ticker)).get();
        } catch (IOException e) {
            log.error("full error text", e);
            throw new RuntimeException(e);
        }
        Elements tdElements = doc.select("tr.table-dark-row").select("td");

        for (Element td : tdElements) {

            String attrs = td.attr("data-boxover");

            // TODO 좀 더 가독성 있게 리팩토링 요망
            if (attrs.contains(PER.getAttr())) {
                finvizDetailRes.setPer(getValue(td));
            } else if (attrs.contains(FORWARD_PER.getAttr())) {
                finvizDetailRes.setFper(getValue(td));
            } else if (attrs.contains(EPS.getAttr())) {
                finvizDetailRes.setEps(getValue(td));
            } else if (attrs.contains(FORWARD_EPS.getAttr())) {
                finvizDetailRes.setFeps(getValue(td));
            } else if (attrs.contains(PEG.getAttr())) {
                finvizDetailRes.setPeg(getValue(td));
            } else if (attrs.contains(RSI.getAttr())) {
                finvizDetailRes.setRsi(getValue(td));
            } else if (attrs.contains(TARGET_PRICE.getAttr())) {
                finvizDetailRes.setTargetPrice(getValue(td));
            } else if (attrs.contains(NOW_PRICE.getAttr())) {
                finvizDetailRes.setNowPrice(getValue(td));
            }

        }

        return finvizDetailRes;
    }

    private Double getValue(Element td) {

        String text = td.nextElementSibling().select("b").text();

        Double value;
        try {
            value = Double.parseDouble(text);
        } catch (NumberFormatException e) { // "-" 과 같은 형태로 주어지는 경우 예외처리
            value = null;
        }

        return value;
    }

}