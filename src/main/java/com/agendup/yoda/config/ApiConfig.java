package com.agendup.yoda.config;

import io.dropwizard.Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiConfig extends Configuration {
    @Valid
    @NotNull
    private CassandraConfig cassandra = new CassandraConfig();

    @JsonProperty("cassandra")
    public CassandraConfig getCassandra() {
        return cassandra;
    }

    @JsonProperty("cassandra")
    public void setCassandra(CassandraConfig factory) {
        this.cassandra = factory;
    }
    
    // @Valid
    // @NotNull
    // private BaseUrlsConfig baseUrls = new BaseUrlsConfig();

    // @JsonProperty("baseUrls")
    // public BaseUrlsConfig getBaseUrls() {
    //     return baseUrls;
    // }

    // @JsonProperty("baseUrls")
    // public void setBaseUrls(BaseUrlsConfig factory) {
    //     this.baseUrls = factory;
    // }
    
    // @Valid
    // @NotNull
    // private SslConfig ssl = new SslConfig();

    // @JsonProperty("ssl")
    // public SslConfig getSsl() {
    //     return ssl;
    // }

    // @JsonProperty("ssl")
    // public void setSsl(SslConfig factory) {
    //     this.ssl = factory;
    // }
    
    // @Valid
    // @NotNull
    // private OvhConfig ovhConfig = new OvhConfig();

    // @JsonProperty("ovh")
    // public OvhConfig getOvh() {
    //     return ovhConfig;
    // }

    // @JsonProperty("ovh")
    // public void setOvh(OvhConfig factory) {
    //     this.ovhConfig = factory;
    // }
    
    // @Valid
    // @NotNull
    // private GoogleConfig google = new GoogleConfig();

    // @JsonProperty("google")
    // public GoogleConfig getGoogle() {
    //     return google;
    // }

    // @JsonProperty("google")
    // public void setGoogle(GoogleConfig factory) {
    //     this.google = factory;
    // }
    
    // @Valid
    // @NotNull
    // private HitCountConfig hitCount = new HitCountConfig();

    // @JsonProperty("hitCount")
    // public HitCountConfig getHitCount() {
    //     return hitCount;
    // }

    // @JsonProperty("hitCount")
    // public void setHitCount(HitCountConfig factory) {
    //     this.hitCount = factory;
    // }
    
    // @Valid
    // @NotNull
    // private JwtConfig jwt = new JwtConfig();

    // @JsonProperty("jwt")
    // public JwtConfig getJwt() {
    //     return jwt;
    // }

    // @JsonProperty("jwt")
    // public void setJwt(JwtConfig factory) {
    //     this.jwt = factory;
    // }
    
    // @Valid
    // @NotNull
    // private MailjetConfig mailjet = new MailjetConfig();

    // @JsonProperty("mailjet")
    // public MailjetConfig getMailjet() {
    //     return mailjet;
    // }

    // @JsonProperty("mailjet")
    // public void setMailjet(MailjetConfig factory) {
    //     this.mailjet = factory;
    // }

    // @Valid
    // @NotNull
    // private SendinblueConfig sendinblue = new SendinblueConfig();

    // @JsonProperty("sendinblue")
    // public SendinblueConfig getSendinblue() {
    //     return sendinblue;
    // }

    // @JsonProperty("sendinblue")
    // public void setSendinblue(SendinblueConfig factory) {
    //     this.sendinblue = factory;
    // }

    // @Valid
    // @NotNull
    // private MailgunConfig mailgun = new MailgunConfig();

    // @JsonProperty("mailgun")
    // public MailgunConfig getMailgun() {
    //     return mailgun;
    // }

    // @JsonProperty("mailgun")
    // public void setMailgun(MailgunConfig factory) {
    //     this.mailgun = factory;
    // }

    // @Valid
    // @NotNull
    // private VosFacturesConfig vosFactures = new VosFacturesConfig();

    // @JsonProperty("vosFactures")
    // public VosFacturesConfig getVosFactures() {
    //     return vosFactures;
    // }

    // @JsonProperty("vosFactures")
    // public void setVosFactures(VosFacturesConfig factory) {
    //     this.vosFactures = factory;
    // }

    // @Valid
    // @NotNull
    // private MoneticoConfig monetico = new MoneticoConfig();

    // @JsonProperty("monetico")
    // public MoneticoConfig getMonetico() {
    //     return monetico;
    // }

    // @JsonProperty("monetico")
    // public void setMonetico(MoneticoConfig factory) {
    //     this.monetico = factory;
    // }

}