package cn.tanglaoer.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author： tks
 * @date： 2023/3/2
 */
@Configuration
@MapperScan("cn.tanglaoer.demo.mapper")
public class MybatisConfig {
}
