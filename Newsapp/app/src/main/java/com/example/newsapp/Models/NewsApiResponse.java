package com.example.newsapp.Models;

import java.io.Serializable;
import java.util.List;

public class NewsApiResponse implements Serializable {
    String status;
    int totalResults;

    List<NewsHeadlines> articles;

    public String getStatus() {
        return status;
    }



    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResult() {
        return totalResults;
    }

    public void setTotalResult(int totalResult) {
        this.totalResults = totalResults;
    }

    public List<NewsHeadlines> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsHeadlines> articles) {
        this.articles = articles;
    }
}
