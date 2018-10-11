package com.yd.wxpay.service.impl;

import com.github.wxpay.sdk.WXPayUtil;
import com.yd.wxpay.common.data.util.HttpClient;
import com.yd.wxpay.service.WeiXinPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeiXinPayServiceImpl implements WeiXinPayService {
    @Autowired
    private Environment env;
    private static Logger log = LoggerFactory.getLogger(WeiXinPayServiceImpl.class);
    @Override
    public Map createNative(String out_trade_no, String total_fee) {
        //1.参数封装
        Map param = new HashMap();
        //公众账户id
        param.put("appid",env.getProperty("apppid"));
        //商户号
        param.put("mch_id",env.getProperty("partner"));
        //随机字符串
        param.put("nonce_str", WXPayUtil.generateNonceStr());
        //商品描述
        param.put("body","BTY");
        //商户订单号
        param.put("out_trade_no",out_trade_no);
        //标价金额
        param.put("total_fee",total_fee);
        //终端ip
        param.put("spbill_create_ip","127.0.0.1");
        //通知地址
        param.put("notify_url","www.baidu.com");
        //交易类型
        param.put("trade_type","NATIVE");

        try {
            String paramXml = WXPayUtil.generateSignedXml(param,env.getProperty("partnerkey"));
            System.out.println("partnerkey:"+env.getProperty("partnerkey"));
            System.out.println("请求的参数:"+paramXml);

            //2.发送请求
            HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            httpClient.setHttps(true);
            httpClient.setXmlParam(paramXml);
            httpClient.post();

            //3.获取结果
            String xmlResult = httpClient.getContent();
            Map<String,String> mapResult = WXPayUtil.xmlToMap(xmlResult);

            System.out.println("微信返回的结果:"+mapResult);

            Map map = new HashMap();
            map.put("code_url",mapResult.get("code_url"));
            map.put("out_trade_no",out_trade_no);
            map.put("total_fee",total_fee);

            return map;
        }catch (Exception e){
            log.info("发送请求获取私钥失败"+e.toString());
            return new HashMap();
        }
    }

    @Override
    public Map queryPayStatus(String out_trade_no) {
        //1.封装参数
        Map param = new HashMap();

        //公众账户id
        param.put("appid",env.getProperty("apppid"));
        //商户号
        param.put("mch_id",env.getProperty("partner"));
        //商户订单号
        param.put("out_trade_no",out_trade_no);
        //随机字符串
        param.put("nonce_str", WXPayUtil.generateNonceStr());
        try {
            //生成签名的xml
            String paramXml = WXPayUtil.generateSignedXml(param,env.getProperty("partnerkey"));
            //2.发送请求
            HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            httpClient.setHttps(true);
            httpClient.setXmlParam(paramXml);
            httpClient.post();

            //3.获取结果
            String xmlResult = httpClient.getContent();
            Map<String,String> map = WXPayUtil.xmlToMap(xmlResult);
            System.out.println("调用查询API返回结果:"+xmlResult);

            return map;
        }catch (Exception e){
            log.info("查询异常:"+e.toString());
            return new HashMap();
        }
    }
}
