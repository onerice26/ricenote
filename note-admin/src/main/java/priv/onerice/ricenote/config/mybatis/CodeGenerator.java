package priv.onerice.ricenote.config.mybatis;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author onerice
 * @date 2023/3/28
 * @apiNote
 */
public class CodeGenerator {
    private static final String author = "onerice";
    private static final String pak = "com.onerice.ricenote.core";
    private static final String tables = "sys_user_org_role";

    public static void main(String[] args) {
        FastAutoGenerator.create(new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3306/rice_note?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8&allowMultiQueries=true&autoReconnect=true",
                        "root", "root"))
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(author).fileOverride().outputDir("E:\\my\\project\\ricenote\\note-admin\\src\\main\\java\\"))
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(pak))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(tables))
                        .controllerBuilder().enableRestStyle().enableHyphenStyle()
                        .entityBuilder().enableLombok().addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("updated_time", FieldFill.INSERT)
                        ).build())
                // 模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                //.templateEngine(new BeetlTemplateEngine())
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
