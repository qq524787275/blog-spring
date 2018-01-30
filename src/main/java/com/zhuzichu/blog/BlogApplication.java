package com.zhuzichu.blog;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

@SpringBootApplication
@MapperScan("com.zhuzichu.blog.dao")
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(new Object[]{BlogApplication.class}, args);
	}
	@Bean
	public HttpMessageConverters fastHttpMessageConverters(){
		FastJsonHttpMessageConverter converter=new FastJsonHttpMessageConverter();
		FastJsonConfig config=new FastJsonConfig();
		config.setSerializerFeatures(SerializerFeature.PrettyFormat);
		converter.setFastJsonConfig(config);
		return new HttpMessageConverters(converter);
	}
}
