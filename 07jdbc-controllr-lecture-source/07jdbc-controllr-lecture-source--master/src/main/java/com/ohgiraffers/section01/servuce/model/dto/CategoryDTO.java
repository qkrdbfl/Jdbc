package com.ohgiraffers.section01.servuce.model.dto;

public class CategoryDTO {

    private  int code;
    private String name;
    private Integer refCategoryCode; //null값도 받기위해 Integer로 씀 int로도 구동 되지만 널값은 0으로 안받아와짐

    public CategoryDTO() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(Integer refCategoryCode) {
        this.refCategoryCode = refCategoryCode;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                '}';
    }

    public CategoryDTO(int code, String name, Integer refCategoryCode) {
        this.code = code;
        this.name = name;
        this.refCategoryCode = refCategoryCode;



    }
}
