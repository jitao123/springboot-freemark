package com.example.generatecode1225.controller;

import com.example.generatecode1225.model.ResultVo;
import com.example.generatecode1225.model.TableClass;
import com.example.generatecode1225.service.GenerateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description:
 * @author: AT
 * @Date: 2021/1/4 2:53 下午
 */
@RestController
public class GenerateCodeController {

    @Autowired
    private GenerateCodeService GenerateCodeService;

    @RequestMapping("/generatecode")
    public ResultVo generatecode(@RequestBody List<TableClass> tableClasses,
                                 HttpServletRequest request) throws Exception {

        ResultVo resultVo = GenerateCodeService.generateCode(tableClasses, request.getServletContext().getRealPath("/"));
        return resultVo;
    }
}
