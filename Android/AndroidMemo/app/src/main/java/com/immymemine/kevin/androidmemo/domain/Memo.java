package com.immymemine.kevin.androidmemo.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by quf93 on 2017-09-19.
 */

public class Memo { //TODO null 처리
    private static final String DELIMETER = "_=_";
    private int no;
    private String subject;
    private String content;
    private String author;
    private String datetime;

    public Memo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.datetime = sdf.format(new Date(System.currentTimeMillis()));
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("no").append(DELIMETER).append(no).append("\n");
        result.append("subject").append(DELIMETER).append(subject).append("\n");
        result.append("author").append(DELIMETER).append(author).append("\n");
        result.append("datetime").append(DELIMETER).append(datetime).append("\n");
        result.append("content").append(DELIMETER).append(content).append("\n");

        return result.toString();
    }
    public void setMemo(String text) {
        String[] elements = text.split("\n");
        String key = "";
        String value = "";
        for(String item:elements) {
            int index = item.indexOf("_");

            if(index != -1) {
                key = item.substring(0, index);
                value = item.substring(item.lastIndexOf("_")+1, item.length());
                switch (key) {
                    case "no":
                        setNo(Integer.parseInt(value));
                        break;
                    case "subject":
                        setSubject(value);
                        break;
                    case "author":
                        setAuthor(value);
                        break;
                    case "datetime":
                        setDatetime(value);
                        break;
                    case "content":
                        setContent(value);
                        break;
                }
            } else {
                value = item;
                content += "\n" + value;
            }
        }
    }

    public byte[] toBytes() {
        return toString().getBytes(); // 문자열에서 Byte 배열로..
    }
    public int getNo() {
        return no;
    }
    public String getSubject() {
        return subject;
    }
    public String getContent() {
        return content;
    }
    public String getAuthor() {
        return author;
    }
    public String getDateTime() {
        return datetime;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setDatetime(String datetime) { this.datetime = datetime; }
}

/*
 글 번호
 제목
 작성자
 작성일자
 내용
 */