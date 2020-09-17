package com.hong.services.config;

import com.hong.common.utils.RsaUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Author : KongJHong
 * @Date : 2020-08-19 23:00
 * @Version : 1.0
 * Description     : 管理公钥私钥
 */
@ConfigurationProperties("rsa.key")
public class RsaKeyProperties {
    // 一定要对应，否则spring加载不到
    private String pubKeyFile;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @PostConstruct // 在这个spring对象构造完成之后执行
    public void createRsaKey() throws Exception {
        publicKey = RsaUtil.getPublicKey(pubKeyFile);
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public String getPubKeyFile() {
        return pubKeyFile;
    }

    public void setPubKeyFile(String pubKeyFile) {
        this.pubKeyFile = pubKeyFile;
    }
}
