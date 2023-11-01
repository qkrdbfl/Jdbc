package com.ohgiraffers.section01.model.dto;

import com.ohgiraffers.section01.servuce.model.dto.OrderMenuDTO;

import java.util.List;

public class OrderDTO {

    private int code;
    private String date;
    private String time;
    private int totalOrderPrice;
    private List<OrderMenuDTO> orderMenuList; // 주문건들 리스트로 묶기

    public OrderDTO() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(int totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public List<OrderMenuDTO> getOrderMenuList() {
        return orderMenuList;
    }

    public void setOrderMenuList(List<OrderMenuDTO> orderMenuList) {
        this.orderMenuList = orderMenuList;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "code=" + code +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", totalOrderPrice=" + totalOrderPrice +
                ", orderMenuList=" + orderMenuList +
                '}';
    }

    public OrderDTO(int code, String date, String time, int totalOrderPrice, List<OrderMenuDTO> orderMenuList) {
        this.code = code;
        this.date = date;
        this.time = time;
        this.totalOrderPrice = totalOrderPrice;
        this.orderMenuList = orderMenuList;


    }
}
