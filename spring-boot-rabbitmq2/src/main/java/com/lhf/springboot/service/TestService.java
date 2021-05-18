package com.lhf.springboot.service;

import com.lhf.springboot.common.ServerResponse;
import com.lhf.springboot.pojo.Mail;

/**
 * @ClassName: TestService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/16 10:27
 */
public interface TestService {

    ServerResponse testIdempotence();

    ServerResponse accessLimit();

    ServerResponse send(Mail mail);
}
