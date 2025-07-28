package com.Ai.Courier;

import com.fasterxml.jackson.databind.JsonNode;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.renderer.html.HtmlRenderer;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.stereotype.Component;


import org.commonmark.parser.Parser;

import java.util.List;

@Component
public class ParseAndRender {


    public String ParseAndRenderMethod(String response){

        Parser parser=Parser.builder().build();
        HtmlRenderer renderer= HtmlRenderer.builder().extensions(List.of(TablesExtension.create())).build();

        Node root=parser.parse(response);

        String render=renderer.render(root);

        return Jsoup.clean(render, Safelist.relaxed());



    }
}
