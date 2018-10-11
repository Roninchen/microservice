package com.yd.wxpay.controller;

import com.yd.wxpay.common.data.ResponseResult;
import com.yd.wxpay.common.data.util.IdWorker;
import com.yd.wxpay.service.WeiXinPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private Environment environment;
    @Autowired
    private WeiXinPayService weiXinPayService;
    @RequestMapping("/createNative")
    public Map createNative(){
        IdWorker idWorker = new IdWorker();
        System.out.println("id==="+idWorker.nextId());
        return weiXinPayService.createNative(idWorker.nextId()+"","1");
    }
    @RequestMapping("/queryPayStatus")
    public ResponseResult queryPayStatus(String out_trade_no){
        ResponseResult result = null;
        int x=0;
        while (true){
            Map<String,String> map = weiXinPayService.queryPayStatus(out_trade_no);
            if (map==null){
                result = new ResponseResult(500,false,"支付发生错误",null);
                break;
            }
            if (map.get("trade_state").equals("SUCCESS")){
                result = new ResponseResult(200,true,"支付成功",null);
                break;
            }
            try {
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
            x++;
            if (x>=100){
                result = new ResponseResult(500,true,"二维码超时",null);
                return result;
            }
        }
        return result;
    }
}
