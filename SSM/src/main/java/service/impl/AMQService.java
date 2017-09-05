package service.impl;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.*;


/**
 * ActiveMQ的消费者
 */
@Service
public class AMQService implements MessageListener {

    private String url;
    private String username;
    private String psw;
    private String queueName;
    private Boolean useAMQ;

    @Override
    public void onMessage(Message message) {

        System.out.println("监听到消息");
        if (message instanceof TextMessage) {
            //强制转换一下
            TextMessage txtMsg = (TextMessage) message;
            System.out.println("");
        }else{
            System.out.println("");
        }
    }

    public void receive() {
        try {
            // 通过username,password,url创建连接工厂接口
            ConnectionFactory factory = new ActiveMQConnectionFactory(this.getUsername(), this.getPsw(), this.getUrl());
            // 通过连接工厂创建一个新的连接接口
            Connection connection = factory.createConnection();
            //打开连接
            connection.start();
            // 通过连接接口创建一个会话接口
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // 会话接口创建有关主题的目标接口
            Destination destination = session.createQueue(this.getQueueName());
            // 会话接口再根据目标接口来创建一个消息消费者接口
            MessageConsumer consumer = session.createConsumer(destination);

            //配置监听
            consumer.setMessageListener(this);

            System.out.println("开始监听消息，Client ID: " + connection.getClientID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，
    // 类似于Serclet的inti()方法。被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。
    @PostConstruct
    public void init(){
        System.out.println("执行postConstruct方法");
        System.out.println("初始化日志Consumer......");
        if(this.useAMQ){
            receive();
        }else{
            System.out.println("不启动监听AMQ消息\n");
        }
    }


    //被@PreConstruct修饰的方法会在服务器卸载Servlet的时候运行，并且只会被服务器调用一次，
    //类似于Servlet的destroy()方法。被@PreDestroy修饰的方法会在destroy()方法之前运行，在Servlet被彻底卸载之前。
    @PreDestroy
    public void destory(){
        System.out.println("执行preDestory方法");
    }




    public String getUrl() {
        return url;
    }

    @Value("#{propertiesReader['amq.op.url']}")
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    @Value("#{propertiesReader['amq.op.username']}")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPsw() {
        return psw;
    }

    @Value("#{propertiesReader['amq.op.password']}")
    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getQueueName() {
        return queueName;
    }

    @Value("#{propertiesReader['amq.op.queueName']}")
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Boolean getUseAMQ() {
        return useAMQ;
    }

    @Value("#{propertiesReader['amq.op.on']}")
    public void setUseAMQ(Boolean useAMQ) {
        this.useAMQ = useAMQ;
    }
}