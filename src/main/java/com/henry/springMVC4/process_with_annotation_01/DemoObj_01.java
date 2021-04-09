package com.henry.springMVC4.process_with_annotation_01;

public class DemoObj_01 {
    private Long id;
    private String name;

    // note：使用Jackson 来 进行 java对象与json对象之间的转换时，必须要使用到构造函数
    public DemoObj_01() {
        super();
    }

    public DemoObj_01(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
