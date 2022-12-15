package pb.guestbook.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DatabaseConfig {

    @Bean
    @Qualifier("postgresDb")
    public HikariDataSource dataSource(
        @Value("${db.pb-guestbook.username}") String username,
        @Value("${db.pb-guestbook.password}") String password,
        @Value("${db.pb-guestbook.jdbcUrl}") String url,
        @Value("${db.pb-guestbook.max.pool.size}") int maxPoolSize,
        @Value("${db.pb-guestbook.idleTimeout}") int idleTimeout,
        @Value("${db.pb-guestbook.driver}") String driver
    ) {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setDriverClassName(driver);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(maxPoolSize);
        hikariConfig.setMinimumIdle(0);
        hikariConfig.setIdleTimeout(idleTimeout);

        return new HikariDataSource(hikariConfig);
    }

    @Bean("guestbookPostgresDb")
    public NamedParameterJdbcTemplate guestbookNamedJdbc(@Qualifier("postgresDb") HikariDataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(@Qualifier("postgresDb") HikariDataSource dataSource) {
        return Flyway.configure()
            .dataSource(dataSource)
            .outOfOrder(true)
            .locations("classpath:/db/flyway")
            .cleanDisabled(true)
            .baselineOnMigrate(true)
            .load();
    }
}
