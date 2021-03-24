package com.home.lh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("**.mapper") // 扫描mybaits注解包 使用后无需再@mapper
public class WxdtApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxdtApplication.class, args);
	}

}
