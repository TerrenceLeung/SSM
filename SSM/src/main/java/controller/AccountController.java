package controller;

import core.annotation.YLog;
import model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.impl.AccountService;
import utils.Json;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by YannLeung on 2017/7/27.
 */
@Controller
@RequestMapping(value = "account")
public class AccountController {

    @Autowired
    @Qualifier("accountService")
    private AccountService accountService;


















    @RequestMapping(value = "selectByCondition", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = POST)
    @ResponseBody
    public Json selectByCondition(@RequestBody Account account) {
        accountService.add(account);
        return new Json();
    }


    @RequestMapping(value = "add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = POST)
    @ResponseBody
    public Json add(@RequestBody Account account) {
        accountService.add(account);
        return new Json();
    }


    @RequestMapping(value = "test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = POST)
    @ResponseBody
    @YLog(moduleName = "account", desc = "测试数据")
    public Json test(@RequestBody Account account){

        System.out.println("执行test方法");
        try{
        }catch(Throwable e){
            throw new RuntimeException("sfsdg");
        }
        finally{
            return Json.success("jahah");
        }
    }





}
