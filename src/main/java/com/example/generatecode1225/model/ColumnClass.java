package com.example.generatecode1225.model;

/**
 * @description: 数据库表的映射
 * @author: AT
 * @Date: 2020/12/31 10:38 上午
 */
public class ColumnClass {
    /**
     * java类的表示
     */
    private String propertiesName;
    /**
     * 数据库类
     */
    private String columnName;
    /**
     * 类型
     */
    private String type;
    /**
     * 备注
     */
    private String remark;
    /**
     *
     */
    private Boolean isPrimary;

    public String getPropertiesName() {
        return propertiesName;
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }


    @Override
    public String toString() {
        return "ColumnClass{" +
                "propertiesName='" + propertiesName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                ", isPrimary=" + isPrimary +
                '}';
    }
}
