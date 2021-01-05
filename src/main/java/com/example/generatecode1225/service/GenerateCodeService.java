package com.example.generatecode1225.service;

import com.example.generatecode1225.model.ColumnClass;
import com.example.generatecode1225.model.ResultVo;
import com.example.generatecode1225.model.TableClass;
import com.example.generatecode1225.utils.DbUtils;
import com.google.common.base.CaseFormat;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: AT
 * @Date: 2021/1/4 2:58 下午
 */
@Service
public class GenerateCodeService {


    // 根据自己引入的freemarker 版本 编写配置类
    Configuration config = null;

    {
        config = new Configuration(Configuration.VERSION_2_3_0);
        // 设置模版的位置
        config.setTemplateLoader(new ClassTemplateLoader(GenerateCodeService.class, "/templates"));

        // 设置字符集
        config.setDefaultEncoding("UTF-8");
    }


    /**
     * 生成代码的逻辑
     *
     * @param tableClasses
     * @param realPath
     */
    public ResultVo generateCode(List<TableClass> tableClasses, String realPath) throws Exception {

        // 获取配置模版文件
        Template modelTemplate = config.getTemplate("Model.java.ftl");

        Connection connection = DbUtils.getConnection();

        DatabaseMetaData metaData = connection.getMetaData();
        for (TableClass tableClass : tableClasses) {

            // 获取所有的列
            ResultSet columns = metaData.getColumns(connection.getCatalog(), null, tableClass.getTableName(), null);
            // 获取所有的primaryKey
            ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), null, tableClass.getTableName());

            List<ColumnClass> classes = new ArrayList<>();

            while (columns.next()) {
                String column_name = columns.getString("COLUMN_NAME");
                String type_name = columns.getString("TYPE_NAME");
                String remarks = columns.getString("REMARKS");
                ColumnClass columnClass = new ColumnClass();
                columnClass.setColumnName(column_name);
                columnClass.setRemark(remarks);
                columnClass.setType(type_name);
                columnClass.setPropertiesName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, column_name));
                // 设置游标归0
                primaryKeys.first();
                while (primaryKeys.next()) {
                    String pkName = primaryKeys.getString("COLUMN_NAME");
                    if (column_name.equals(pkName)) {
                        columnClass.setPrimary(true);
                    }
                }
                // 设置列
                classes.add(columnClass);
            }
            tableClass.setColumns(classes);
            String path = realPath + File.separator + tableClass.getPackageName().replace(".", File.separator);
            generate(modelTemplate, tableClass, path + File.separator + "model" + File.separator);
        }

        return ResultVo.Ok("代码生成已完成～",realPath);

    }

    private void generate(Template modelTemplate, TableClass tableClass, String path) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
//        设置通用的可替换的文件名
        String fileName = path + File.separator + tableClass.getModuleName() + modelTemplate.getName().replace(".ftl", "").replace("Model", "");
        // 文件输出流
        FileOutputStream fileOut = new FileOutputStream(fileName);
//        写出流
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOut);
        // 使用freemark 的文件写出方法
        modelTemplate.process(tableClass,outputStreamWriter);
        fileOut.close();
        outputStreamWriter.close();
///Users/edz/Downloads/allCodeWorkSpace/learnJaveCode/generatecode1225
    }
}
