package com.hong.auth.config;

import com.hong.common.utils.RsaUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Author : KongJHong
 * @Date : 2020-09-07 11:28
 * @Version : 1.0
 * Description     : 管理公钥私钥属性类
 */
@ConfigurationProperties("rsa.key")
public class RsaKeyProperties {

    // 一定要对应，否则spring加载不到
    private String pubKeyFile;
    private String priKeyFile;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @PostConstruct // 在这个bean对象构造完之后调用，这样能保证pubKeyFile和priKeyFile是有值的
    public void createRsaKey() throws Exception {
        publicKey = RsaUtil.getPublicKey(pubKeyFile);
        privateKey = RsaUtil.getPrivateKey(priKeyFile);
    }

    public PublicKey getPublicKey(){
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public String getPubKeyFile() {
        return pubKeyFile;
    }

    public void setPubKeyFile(String pubKeyFile) {
        this.pubKeyFile = pubKeyFile;
    }

    public String getPriKeyFile() {
        return priKeyFile;
    }

    public void setPriKeyFile(String priKeyFile) {
        this.priKeyFile = priKeyFile;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }
}
