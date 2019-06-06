package lk.savoy.bean.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@ComponentScan("lk.savoy.bean")
@Configuration
@Import(JpaConfig.class)
public class AppConfig {
}
