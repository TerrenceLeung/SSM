package service.impl;

import dao.AccountDao;
import model.Account;
import org.apache.commons.collections.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YannLeung on 2017/7/27.
 */
@Service("accountService")
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    public void add(Account account){
        Account account1 = accountDao.selectByPrimaryKey(1);
        if(account1 != null){
            System.out.println(account1.getNickname());
        }

        List<Account> accounts = accountDao.selectByOpt(10,"tom");
        if(accounts != null && accounts.size()>0){
            System.out.println(accounts.get(0).getNickname());
        }

//        accounts = accountDao.selectByCondition("nickname");
//        if(accounts != null && accounts.size()>0){
//            System.out.println(accounts.get(0).getName());
//        }

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        accounts = accountDao.selectByIdIn(list);
        if(accounts != null && accounts.size()>0){
            System.out.println(accounts.get(0).getNickname());
        }

        accounts = accountDao.selectByName("tom",null);
        if(accounts != null && accounts.size()>0){
            System.out.println(accounts.get(0).getNickname());
        }

    }
}
