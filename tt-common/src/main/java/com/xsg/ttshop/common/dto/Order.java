package com.xsg.ttshop.common.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * User: 57140
 * Date: 2017/11/22
 * Time: 13:50
 * Version:V1.0
 */
public class Order {
    private String sort;
    private  String order;
//    private List<String> orderParams;
    public List<String> getOrderParams() {
        String[] sorts = this.sort.split(",");
        String[] orders = this.order.split(",");
        List<String> list = new ArrayList<String>();
        for (int i =0; i<sorts.length; i++){
            String temp = sorts[i] +" "+orders[i];
            list.add(temp);
        }
        return list;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
