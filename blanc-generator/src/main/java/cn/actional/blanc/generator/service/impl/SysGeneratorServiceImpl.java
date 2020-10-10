package cn.actional.blanc.generator.service.impl;

import cn.actional.blanc.common.utils.PageUtils;
import cn.actional.blanc.common.utils.QueryParam;
import cn.actional.blanc.generator.mapper.SysGeneratorMapper;
import cn.actional.blanc.generator.service.ISysGeneratorService;
import cn.actional.blanc.generator.utils.GeneratorUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 9/26/20 9:36 PM
 */
@Service
public class SysGeneratorServiceImpl implements ISysGeneratorService {

    private SysGeneratorMapper sysGeneratorMapper;

    @Autowired
    public void setSysGeneratorMapper(SysGeneratorMapper sysGeneratorMapper) {
        this.sysGeneratorMapper = sysGeneratorMapper;
    }

    @Override
    public byte[] generatorCode(String[] tables) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(bo);
        generatorCode(tables,zip);
        IOUtils.closeQuietly(zip);
        return bo.toByteArray();
    }

    @Override
    public PageUtils queryList(QueryParam queryParam) {
        PageHelper.startPage(queryParam.getPage(), queryParam.getLimit());
        List<Map<String, Object>> maps = sysGeneratorMapper.queryList(queryParam);
        PageInfo<Map<String, Object>> page = new PageInfo<>(maps);
        return new PageUtils(
                (int) page.getTotal(),page.getPageNum(),
                page.getPages(),page.getList(),
                page.isHasPreviousPage(),page.isHasNextPage()
        );
    }

    private void generatorCode(String[] tables, ZipOutputStream zip) {
        for (String table : tables) {
            //获取表信息
            Map<String, String> tableInfo = sysGeneratorMapper.selectTableByTableName(table);

            //获取表字段信息
            List<Map<String, String>> tableColumns =  sysGeneratorMapper.selectColumnsByTableName(table);

            // 生成代码
            GeneratorUtil.generatorCode(tableInfo, tableColumns,zip);
        }

    }
}
