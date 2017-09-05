package core;



import core.annotation.YLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import utils.Json;

/**
 * Created by YannLeung on 2017/8/8.
 */


@Aspect
@Component
@Order(20)
public class LogAspect {

    Logger logger = LoggerFactory.getLogger(this.getClass());

//    输出以下内容...
//    @Around方法前
//    @Before方法
//    执行test方法
//    @Around方法后
//    @After方法
//    @AfterReturning方法



    //在所有使用@Controller注解的方法前使用
    @Before(value = "execution(* controller.AccountController.test(*))")
    public void logBeforeController(JoinPoint point){
        Object[] args = point.getArgs();
        Object target = point.getTarget();
        String kind = point.getKind();
        Signature signature = point.getSignature();
        echo("@Before方法,调用"+target+"的（"+signature+"）,调用类型为"+kind);
    }

    //在所有使用@Controller注解的方法前使用
    //与@Around不兼容,try-catch无法捕抓,方法不需要throws Exception,AfterReturning不起效
    @AfterThrowing(value = "execution(* controller.AccountController.test(*))", throwing="exeception")
    public void logAfterThrowing(JoinPoint point,Exception exeception){
        echo("@AfterThrowing,捕获到抛出的异常"+exeception);
    }


    //在所有使用@Controller注解的方法前后插入使用
    @Around(value = "execution(* controller.AccountController.test(*)) && @annotation(log)")
    public Object logAroundController(ProceedingJoinPoint joinPoint, YLog log){
        System.out.println("@Around方法前");
        Object json = null;
        try {
            json = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("@Around方法后");
        return json;
    }

    //在所有使用@Controller注解的方法后使用
    @After(value = "execution(* controller.AccountController.test(*))")
    public void logAfterController(JoinPoint point){
        Object[] args = point.getArgs();
        Object target = point.getTarget();
        String kind = point.getKind();
        Signature signature = point.getSignature();
        echo("@After方法,调用"+target+"的（"+signature+"）,调用类型为"+kind);
    }


    //在所有使用@Controller注解的方法后使用
    @AfterReturning(value = "execution(* controller.AccountController.test(*))", returning="returnValue")
    public void logAfterController(JoinPoint point,Object returnValue){
        Object[] args = point.getArgs();
        Object target = point.getTarget();
        String kind = point.getKind();
        Signature signature = point.getSignature();
        echo("@AfterReturning方法,调用"+target+"的（"+signature+"）,调用类型为"+kind+",返回的参数是"+returnValue);
    }




//    @Around(value = "@within(org.springframework.stereotype.Controller)")
//    @Around(value = "@within(org.springframework.stereotype.Service)")
//    public void logAroundService(){
//        System.out.println("执行了Service@Around方法");
//    }





    private void echo(String str){
        System.out.println(str);
    }
}
