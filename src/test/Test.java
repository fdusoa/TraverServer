package test;

import model.User;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import javax.xml.namespace.QName;

/**
 * Created by zihao on 2017/5/1.
 */

public class Test {

    public static void main(String[] args) {
//        SceneDao sceneDao = new SceneDao();
//        List<Scene> list =  sceneDao.getSceneList(0, 5);
//        list.forEach(e -> System.out.println(e.getUrl()));
        call();
//        UserDao dao = new UserDao();
//        User user = dao.getUser("yzh");
//        user.setPassword("abc");
//        user.setUsername("abe");
//        System.out.println(dao.addUser(user));
//
//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword());
//        System.out.println(user.getId());
//        try{
//            factory = new Configuration().configure().buildSessionFactory();
//        }catch (Throwable ex) {
//            System.err.println("Failed to create sessionFactory object." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//        Session session = factory.openSession();
//        Transaction tx = null;
//        Integer employeeID = null;
//        try{
//            tx = session.beginTransaction();
//            User user = new User();
//            user.setPassword("yzh");
//            user.setUsername("yzh");
//            employeeID = (Integer) session.save(user);
//            tx.commit();
//            System.out.println(employeeID);
//        }catch (HibernateException e) {
//            if (tx!=null) tx.rollback();
//            e.printStackTrace();
//        }finally {
//            session.close();
//        }

    }

    public static void call() {
        //使用RPC方式调用WebService
        RPCServiceClient serviceClient = null;
        try {
            serviceClient = new RPCServiceClient();
            Options options = serviceClient.getOptions();
            //指定调用WebService的URL
            EndpointReference targetEPR = new EndpointReference("http://120.76.125.35/axis2/services/UserService");
//            EndpointReference targetEPR = new EndpointReference("http://localhost:8080/axis2/services/UserService");
//            EndpointReference targetEPR = new EndpointReference("http://localhost:8080/axis2/services/SceneService");
            options.setTo(targetEPR);
            //指定sayHello方法的参数值
            User user = new User();
            user.setUsername("hello");
            user.setPassword("hello");
//            Object[] opAddEntryArgs = new Object[] {user};
//            Object[] opAddEntryArgs = new Object[] {"hello", "hello"};
//            Scene scene = new Scene();
//            scene.setUrl("testurl");
//            scene.setLatitude(123.1);
//            scene.setLongitude(123.2);
//            scene.setAuthorId(9);

//            Object[] opAddEntryArgs = new Object[] {"9:1494739712125:93bf5b36e08aaaa45ba7cad849f4e93b", scene};
            Object[] opAddEntryArgs = new Object[] {"hello", "hello"};

            //指定sayHello方法返回值的数据类型的Class对象
            //指定要调用的sayHello方法及WSDL文件的命名空间
//            QName opAddEntry = new QName("http://service", "echo");
            QName opAddEntry = new QName("http://service", "login");
//            QName opAddEntry = new QName("http://service", "uploadScene");
//            QName opAddEntry = new QName("http://service", "getScenePage");
            //调用sayHello方法并输出该方法的返回值
//            User result = (User) serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, new Class[] {User.class})[0];
            String rstring = serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs).toString();
            System.out.println(rstring);
//            OMElement omElement = serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs);
//            System.out.println(omElement.toString() + "\n");
//            omElement.getChildElements().forEachRemaining(e -> System.out.println(e.toString()));
            serviceClient.cleanupTransport();  //为了防止连接超时
//            System.out.println(result.getPassword());
//            System.out.println(result.getUsername());
//            System.out.println(rstring);
        } catch (AxisFault e) {
            e.printStackTrace();
        }
    }
}
