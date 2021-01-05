package com.example.generatecode1225.controller;

import com.example.generatecode1225.model.Db;
import com.example.generatecode1225.model.ResultVo;
import com.example.generatecode1225.model.TableClass;
import com.example.generatecode1225.utils.DbUtils;
import com.google.common.base.CaseFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @description:
 * @author: AT
 * @Date: 2020/12/31 9:50 上午
 */
@RestController
public class DBcontroller {

    @RequestMapping("/initDb")
    public ResultVo getDBConnection(@RequestBody Db db) {
        Connection connection = DbUtils.initDb(db);
        if (Objects.nonNull(connection)) {
            return ResultVo.Ok("数据库链接成功！");
        }
        return ResultVo.err("数据库链接失败！");
    }

    @RequestMapping("/config")
    public ResultVo getTables(@RequestBody Map<String, String> map) {
        String packageName = map.get("packageName");

        try {
            Connection connection = DbUtils.getConnection();
            //  DatabaseMetaData 获取我们连接到的数据库的结构、存储等很多信息。如：
            //         1、数据库与用户，数据库标识符以及函数与存储过程。
            //         2、数据库限制。
            //         3、数据库支持不支持的功能。
            //         4、架构、编目、表、列和视图等。

            DatabaseMetaData metaData = connection.getMetaData();
            // 获取所有的表
            ResultSet tables = metaData.getTables(connection.getCatalog(), null, null, null);
            List<TableClass> tableClasses = new ArrayList<>();
            while (tables.next()) {
                TableClass tableClass = new TableClass();
                String table_name = tables.getString("TABLE_NAME");
                String moduleName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table_name);
                tableClass.setTableName(table_name);
                tableClass.setModuleName(moduleName);
                tableClass.setControllerName(moduleName+"Controller");
                tableClass.setMapperName(moduleName+"Mapper");
                tableClass.setServiceName(moduleName+"Service");
                tableClass.setPackageName(packageName);
                tableClasses.add(tableClass);
            }
            return ResultVo.Ok("数据库读取成功！",tableClasses);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultVo.err("数据库读取失败！");
    }
}
