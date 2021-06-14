package com.company;

import java.util.Objects;

public class URLDepthPair {
    private String url;
    private int depth;

    URLDepthPair(String url,int depth) {
        this.depth = depth;
        this.url = url;
    }

    URLDepthPair(String url) {
        this.url = url;
        this.depth = 0;
    }


    public int getDepth() {
        return depth;
    }

    public String getUrl() {
        return url;
    }


    public String toString() {
        return (url + " " + depth);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        URLDepthPair that = (URLDepthPair) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
