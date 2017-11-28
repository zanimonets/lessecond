package com.benjiweber.html;

import com.benjiweber.html.tags.Article;
import com.benjiweber.html.tags.Body;
import com.benjiweber.html.tags.H1;
import com.benjiweber.html.tags.Head;
import com.benjiweber.html.tags.Html;
import com.benjiweber.html.tags.Img;
import com.benjiweber.html.tags.Li;
import com.benjiweber.html.tags.Link;
import com.benjiweber.html.tags.Meta;
import com.benjiweber.html.tags.P;
import com.benjiweber.html.tags.PhrasingContent;
import com.benjiweber.html.tags.Script;
import com.benjiweber.html.tags.Small;
import com.benjiweber.html.tags.Ul;
import com.benjiweber.html.tags.support.Attribute;
import com.benjiweber.html.tags.support.Tag;
import com.benjiweber.html.values.PixelMeasurement;
import com.benjiweber.html.values.Rel;
import com.benjiweber.html.values.ScriptType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class HtmlDsl {
    public static Article article(Tag... children) {
        return new Article() {
            public String asString() {
                return childrenAsString();
            }

            public List<Tag> children() {
                return asList(children);
            }
        };
    }

    public static Body body(Stream<Tag> children) {
        return body(children.collect(toList()));
    }

    public static Body body(Tag... children) {
        return body(Arrays.asList(children));
    }

    private static Body body(List<Tag> children) {
        return new Body() {
            public String asString() {
                return tag("body");
            }

            public List<Tag> children() {
                return children;
            }
        };
    }

    @SafeVarargs
    public static H1 h1(String content, Attribute<String>... attributes) {
        return () -> "<h1>" + content + "</h1>";
    }

    public static Head head(Head.Child... children) {
        return new Head() {
            public String asString() {
                return tag("head");
            }

            public List<Tag> children() {
                return asList(children);
            }
        };
    }

    public static Html html(Head head, Body body) {
        return new Html() {
            public String asString() {
                return tidy(tag("html"));
            }

            public List<Tag> children() {
                return asList(head, body);
            }
        };
    }

    public static Img img(Attribute<String> src, Attribute<PixelMeasurement> dim1, Attribute<PixelMeasurement> dim2) {
        return new Img() {
            public String asString() {
                return tag("img");
            }

            public List<Attribute> attributes() {
                return asList(dim1, dim2, src);
            }
        };
    }

    public static Li li(String value) {
        return new Li() {
            @Override
            public String asString() {
                return tag("li", value);
            }
        };
    }

    public static Link link(Attribute<Rel> rel, Attribute<?>... attributes) {
        return () -> "";
    }

    @SafeVarargs
    public static Meta meta(Attribute<String>... attributes) {
        return new Meta() {
            public String asString() { return tag("meta");  }
            public List<Attribute> attributes() { return asList(attributes); }
        };
    }

    public static P p(String content, PhrasingContent... children) {
        return new P() {
            public String asString() { return tag("p", content); }
            public List<Tag> children() { return asList(children); }
        };
    }

    public static Script script(Attribute<ScriptType> type, Attribute<String> src) {
        return new Script() {
            public String asString() { return tag("script"); }
            public List<Attribute> attributes() { return asList(type, src); }
        };
    }

    public static Small small(String content) {
        return new Small() {
            @Override
            public String asString() {
                return tag("small", content);
            }
        };
    }

    public static Ul ul(Li... items) {
        return new Ul() {
            public String asString() { return tag("ul"); }
            public List<Tag> children() { return asList(items); }
        };
    }
}
