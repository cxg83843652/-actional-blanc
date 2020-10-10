package cn.actional.blanc.generator.controller;

import cn.actional.blanc.common.utils.PageUtils;
import cn.actional.blanc.common.utils.QueryParam;
import cn.actional.blanc.generator.service.ISysGeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 9/26/20 9:35 PM
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {

    private ISysGeneratorService sysGeneratorService;


    @Autowired
    public void setSysGeneratorService(ISysGeneratorService sysGeneratorService) {
        this.sysGeneratorService = sysGeneratorService;
    }

    @RequestMapping("/list")
    public String list(@RequestParam Map<String, Object> map, Model model) {
        PageUtils page = sysGeneratorService.queryList(new QueryParam(map));
        model.addAttribute("page",page);
        return "table";
    }


    @GetMapping({"/","/index","/index.html"})
    public String index() {
        return "index";
    }

    /**
     *  生成代码
     */
    @ResponseBody
    @RequestMapping("/code")
    public void generatorCode(String[] tables, HttpServletResponse response) throws IOException {
        byte[] data = sysGeneratorService.generatorCode(tables);
        response.setHeader("Content-Disposition", "attachment;filename=\"code.zip\"");
        response.addHeader("Content-Length","" + data.length);
        response.setContentType("application/octet-stream;charset=UTF-8");
        IOUtils.write(data,response.getOutputStream());
    }



}
