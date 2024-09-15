package com.zhiran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableFeignClients("com.zhiran.service")
public class zhiranAccountApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(zhiranAccountApplication.class, args);
    }
}
