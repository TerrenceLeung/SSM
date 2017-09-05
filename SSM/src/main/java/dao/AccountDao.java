package dao;


import model.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AccountDao {

    Account selectByPrimaryKey(Integer id);

    List<Account> selectByCondition(@Param("nickname")String nickname);

    List<Account> selectByOpt(@Param("opt")int opt,@Param("nickname")String nickname);

    List<Account> selectByName(@Param("name")String name,@Param("nickname")String nickname);

    List<Account> selectByIdIn(@Param("list") List list);
}