package al.spree.spring.security.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@ComponentScan("al.spree.spring.security")
@EnableWebMvc
@PropertySource("classpath:hibernate.properties")
public class ConfigSpringSecurity {

    private final Environment env;

    public ConfigSpringSecurity(Environment env) {
        this.env = env;
    }


    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver =new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(env.getRequiredProperty("hibernate.driver_class"));
            dataSource.setJdbcUrl(env.getRequiredProperty("hibernate.connection.url"));
            dataSource.setUser(env.getRequiredProperty("hibernate.connection.username"));
            dataSource.setPassword(env.getRequiredProperty("hibernate.connection.password"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }

}
