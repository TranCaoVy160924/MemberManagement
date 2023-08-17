package dxc.assignment.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import dxc.assignment.mapper.MemberMapper;

@Configuration
public class MyBatisConfig {
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(getDataSource());

//		SqlSessionFactory factory = factoryBean.getObject();
//		factory.getConfiguration().addMapper(MemberMapper.class);
		
//		factoryBean.setMapperLocations(new ClassPathResource("dxc/assignment/mapper/MemberMapper.xml"));

		return factoryBean.getObject();
	}

	@Bean
	public MapperFactoryBean<MemberMapper> memberMapper() throws Exception {
		MapperFactoryBean<MemberMapper> factoryBean = new MapperFactoryBean<>(
				MemberMapper.class);
		factoryBean.setSqlSessionFactory(sqlSessionFactory());
		return factoryBean;
	}

	private DriverManagerDataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/dxc_assignment");
		dataSource.setUsername("postgres");
		dataSource.setPassword("1234567890");

		return dataSource;
	}
}
