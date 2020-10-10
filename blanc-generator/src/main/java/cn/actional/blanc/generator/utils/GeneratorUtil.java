package cn.actional.blanc.generator.utils;

import cn.actional.blanc.common.utils.BaseException;
import cn.actional.blanc.common.utils.DateUtils;
import cn.actional.blanc.generator.entity.ColumnEntity;
import cn.actional.blanc.generator.entity.TableEntity;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 2020-09-29 14:58
 * 生成代码
 */
public class GeneratorUtil {

    private static String currentTableName;

    /**
     * 读取配置文件, 信息是包名，作者，和数据类型对应关系
     *
     * @return 配置
     */
    private static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new BaseException("配置文件读取失败");
        }
    }


    public static void generatorCode(Map<String, String> tableInfo,
                                     List<Map<String, String>> tableColumns,
                                     ZipOutputStream zip) {
        //获取配置
        Configuration config = getConfig();


        boolean hasBigDecimal = false;
        boolean hasList = false;

        //表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(tableInfo.get("tableName"));
        currentTableName = tableInfo.get("tableName");
        tableEntity.setComments(tableInfo.get("tableComment"));
        //表名转换成 Java 类名
        String className = tableToJava(tableEntity.getTableName(), config.getStringArray("tablePrefix"));
        //首字母大写的类名
        tableEntity.setClassName(className);
        // 首字母小写的类名
        tableEntity.setClassname(StringUtils.uncapitalize(className));

        List<ColumnEntity> columnlist = new ArrayList<>();

        for (Map<String, String> column : tableColumns) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.get("columnName"));
            columnEntity.setDataType(column.get("dataType"));
            columnEntity.setExtra(column.get("extra"));
            columnEntity.setComments(column.get("columnComment"));

            String attrName = columnToJava(columnEntity.getColumnName());
            // 首字母大写的字段名
            columnEntity.setAttrName(attrName);
            // 首字母小写的字段名
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            // 列的数据类型， 转换成Java 的数据类型
            String attrType = config.getString(columnEntity.getDataType(), columnToJava(columnEntity.getDataType()));
            columnEntity.setAttrType(attrType);

            if (!hasBigDecimal && "BigDecimal".equals(attrType)) {
                hasBigDecimal = true;
            }

            if (!hasList && "array".equals(columnEntity.getExtra())) {
                hasList = true;
            }

            // 是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null) {
                tableEntity.setPk(columnEntity);
            }

            columnlist.add(columnEntity);
        }

        tableEntity.setColumns(columnlist);

        //没有主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }


        //设置velocity 资源加载器
        Properties properties = new Properties();
        properties.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        Velocity.init(properties);

        String mainPath = config.getString("mainPath");
        mainPath = mainPath == null ? "cn.actional" : mainPath;

        //封装数据模板
        Map<String, Object> map = new HashMap<>();

        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pk", tableEntity.getPk());
        map.put("pathName", tableEntity.getClassName().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("hasList", hasList);
        map.put("mainPath", mainPath);
        map.put("package", config.getString("package"));
        map.put("moduleName", config.getString("moduleName"));
        map.put("author", config.getString("author"));
        map.put("email", config.getString("email"));
        map.put("datetime", DateUtils.format(new Date(), DateUtils.DATA_TIME_PATTERN));

        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();


        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template template1 = Velocity.getTemplate(template, "UTF-8");
            template1.merge(context, sw);

            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template,tableEntity.getClassName(),config.getString("package"), config.getString("moduleName"))));
                IOUtils.write(sw.toString(),zip,"UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new BaseException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
            }
        }

    }


    /**
     * 获取文件名
     */
    public static String getFileName(String template, String className, String packageName, String moduleName) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
        }
        if (template.contains("MongoChildrenEntity.java.vm")) {
            return packagePath + "entity" + File.separator + "inner" + File.separator + currentTableName + File.separator + splitInnerName(className) + "InnerEntity.java";
        }
        if (template.contains("Entity.java.vm") || template.contains("MongoEntity.java.vm")) {
            return packagePath + "entity" + File.separator + className + "Entity.java";
        }

        if (template.contains("Mapper.java.vm")) {
            return packagePath + "mapper" + File.separator + className + "Dao.java";
        }

        if (template.contains("Service.java.vm")) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm")) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm")) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (template.contains("Mapper.vm")) {
            return "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + moduleName + File.separator + className + "Dao.xml";
        }

        if (template.contains("menu.sql.vm")) {
            return className.toLowerCase() + "_menu.sql";
        }

        if (template.contains("index.vue.vm")) {
            return "main" + File.separator + "resources" + File.separator + "src" + File.separator + "views" + File.separator + "modules" +
                    File.separator + moduleName + File.separator + className.toLowerCase() + ".vue";
        }

        if (template.contains("add-or-update.vue.vm")) {
            return "main" + File.separator + "resources" + File.separator + "src" + File.separator + "views" + File.separator + "modules" +
                    File.separator + moduleName + File.separator + className.toLowerCase() + "-add-or-update.vue";
        }

        return null;
    }


    private static String splitInnerName(String name) {
        name = name.replaceAll("\\.", "_");
        return name;
    }

    private static String tableToJava(String tableName, String[] tablePrefixes) {
        if (ArrayUtils.isNotEmpty(tablePrefixes)) {
            for (String tablePrefix : tablePrefixes) {
                // 替换掉表前缀
                tableName = tableName.replace(tablePrefix, "");
            }
        }
        return columnToJava(tableName);
    }

    private static String columnToJava(String tableName) {
        // 首字母转换成大写
        return WordUtils.capitalizeFully(tableName, new char[]{'_'}).replace("_", "");
    }


    public static List<String> getTemplates() {
        ArrayList<String> templates = new ArrayList<>();
        templates.add("templates/codeTemplates/Controller.java.vm");
        templates.add("templates/codeTemplates/Mapper.java.vm");
        templates.add("templates/codeTemplates/Mapper.vm");
        templates.add("templates/codeTemplates/Service.java.vm");
        templates.add("templates/codeTemplates/ServiceImpl.java.vm");
        templates.add("templates/codeTemplates/Entity.java.vm");
        return templates;
    }
}
