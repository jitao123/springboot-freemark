package ${packageName}

import java.util.Date;

public class ${moduleName}{

<#if columns?? >
    <#list columns as column>
        <#if column.type='VARCHAR'||column.type="TEXT"||column.type="CHAR">
            /**
            * ${column.remark}
            */
            private String ${column.propertiesName?uncap_first};
        </#if>
        <#if column.type="INT">
            /**
            *${column.remark}
            */
            private Integer ${column.propertiesName?uncap_first};
        </#if>
        <#if column.type="DATETIME">
            /**
            *${column.remark}
            */
            private Date ${column.propertiesName?uncap_first};
        </#if>
        <#if column.type="BIGINT">
            /**
            *${column.remark}
            */
            private Long ${column.propertiesName?uncap_first};
        </#if>

        <#if column.type="DOUBLE">
            /**
            *${column.remark}
            */
            private Double ${column.propertiesName?uncap_first};
        </#if>

        <#if column.type="BIT">
            /**
            *${column.remark}
            */
            private Boolean ${column.propertiesName?uncap_first};
        </#if>

        <#if column.type="FLOAT">
            /**
            *${column.remark}
            */
            private Float ${column.propertiesName?uncap_first};
        </#if>


    </#list>
</#if>


<#if columns?? >
<#--        生成get、set 方法-->
    <#list columns as column>
        <#if column.type='VARCHAR'||column.type="TEXT"||column.type="CHAR">
            public String get${column.propertiesName}(){
                return ${column.propertiesName?uncap_first};
            }
            public void set${column.propertiesName}(String ${column.propertiesName?uncap_first}){
                this.${column.propertiesName?uncap_first}= ${column.propertiesName?uncap_first};
            }
        </#if>
        <#if column.type="INT">
            public Integer get${column.propertiesName}(){
                return ${column.propertiesName?uncap_first};
            }

            public void set${column.propertiesName}(Integer ${column.propertiesName?uncap_first}){
                this.${column.propertiesName?uncap_first}=${column.propertiesName?uncap_first};
            }
        </#if>
        <#if column.type="DATETIME">
            public Date get${column.propertiesName}(){
                return ${column.propertiesName?uncap_first};
            }

            public void  set${column.propertiesName}(Date ${column.propertiesName?uncap_first}){
                this.${column.propertiesName?uncap_first}=${column.propertiesName?uncap_first};
            }
        </#if>
        <#if column.type="BIGINT">
            public  long get${column.propertiesName}(){
                return  ${column.propertiesName?uncap_first};
            }
            public void set${column.propertiesName}(long ${column.propertiesName?uncap_first} ){
                this.${column.propertiesName?uncap_first}=${column.propertiesName?uncap_first};
            }
        </#if>

        <#if column.type="DOUBLE">
            public  Double ${column.propertiesName?uncap_first}(){
                return this.${column.propertiesName};
            }

            public  void  set${column.propertiesName}(Double ${column.propertiesName?uncap_first} ){
                this.${column.propertiesName?uncap_first}=${column.propertiesName?uncap_first};
            }
        </#if>

        <#if column.type="BIT">
            public  Boolean get${column.propertiesName?uncap_first}(){
                return this.${column.propertiesName?uncap_first};
            }
            public void set${column.propertiesName}(boolean ${column.propertiesName?uncap_first}){
                this.${column.propertiesName?uncap_first}= ${column.propertiesName?uncap_first};
            }
        </#if>


        <#if column.type="FLOAT">
            public float  get${column.propertiesName?uncap_first}(){
            return this.${column.propertiesName?uncap_first};
            }
            public void set${column.propertiesName}(float ${column.propertiesName?uncap_first}){
            this.${column.propertiesName?uncap_first}= ${column.propertiesName?uncap_first};
            }
        </#if>
    </#list>
</#if>


<#--// uncap_first 自定义内置函数，如果首字母是大写，那么会将首字母转换为小写-->
}


