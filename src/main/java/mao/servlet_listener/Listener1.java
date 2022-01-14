package mao.servlet_listener;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * Project name(项目名称)：Servlet_Listener
 * Package(包名): ${PACKAGE_NAME}
 * Class(类名): ${NAME}
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/1/14
 * Time(创建时间)： 21:04
 * Version(版本): 1.0
 * Description(描述)：
 * 监听器 Listener 是一个实现特定接口的 Java 程序，这个程序专门用于监听另一个 Java 对象的方法调用或属性改变，
 * 当被监听对象发生上述事件后，监听器某个方法将立即自动执行。
 * 监听器的相关概念：
 * 事件：方法调用、属性改变、状态改变等。
 * 事件源：被监听的对象（ 例如：request、session、servletContext）。
 * 监听器：用于监听事件源对象 ，事件源对象状态的变化都会触发监听器。
 * 注册监听器：将监听器与事件源进行绑定。
 * 监听器的分类
 * Servlet 规范中定义了 8 个监听器接口，可以用于监听 ServletContext、HttpSession 和 ServletRequest 对象的生命周期和属性变化事件。
 * 开发 Servlet 监听器需要实现相应的监听器接口并重写接口中的方法。
 * 监听器 Listener 按照监听的事件划分，可以分为 3 类：
 * 监听对象创建和销毁的监听器
 * 监听对象中属性变更的监听器
 * 监听 HttpSession 中的对象状态改变的监听器
 * 监听对象创建和销毁的监听器
 * Servlet 规范定义了监听 ServletContext、HttpSession、HttpServletRequest 这三个对象创建和销毁事件的监听器
 * 事件源	            监听器	            监听器描述	            创建和销毁方法	        调用时机
 * ServletContext	ServletContextListener	用于监听 ServletContext 对象的创建与销毁过程	void contextInitialized (ServletContextEvent sce)	当创建 ServletContext 对象时
 * void contextDestroyed (ServletContextEvent sce)	当销毁 ServletContext 对象时
 * HttpSession	HttpSessionListener	用于监听 HttpSession 对象的创建和销毁过程	void sessionCreated (HttpSessionEvent se)	当创建 HttpSession 对象时
 * void sessionDestroyed (HttpSessionEvent se)	当销毁 HttpSession 对象时
 * ServletRequest	ServletRequestListener	用于监听 ServletRequest 对象的创建和销毁过程	void requestInitialized (ServletRequestEvent sre)	当创建 ServletRequest 对象时
 * void requestDestroyed (ServletRequestEvent sre)	当销毁 ServletRequest 对象时
 *
 * 监听属性变更的监听器
 * Servlet 规范定义了监听 ServletContext、HttpSession、HttpServletRequest 这三个对象中的属性变更事件的监听器，
 * 这三个监听器接口分别是 ServletContextAttributeListener、HttpSessionAttributeListener 和 ServletRequestAttributeListener。
 * 这三个接口中都定义了三个方法，用来处理被监听对象中属性的增加，删除和替换事件。同一种事件在这三个接口中对应的方法名称完全相同，只是参数类型不同
 * 事件源              	监听器	                监听器描述               	方法              	调用时机
 * ServletContext	ServletContextAttributeListener	用于监听 ServletContext 对象的属性新增、移除和替换	public void attributeAdded (ServletContextAttributeEvent scae) 	当 ServletContext 对象中新增一个属性时
 * public void attributeRemoved (ServletContextAttributeEvent scae) 	当删除 ServletContext 对象中的一个属性时
 * public void attributeReplaced (ServletContextAttributeEvent scae) 	当 ServletContext 对象中的某个属性被替换时
 * HttpSession	HttpSessionAttributeListener	用于监听 HttpSession 对象的属性新增、移除和替换	public void attributeAdded  (HttpSessionBindingEvent  hsbe) 	当 HttpSession 对象中新增一个属性时
 * public void attributeRemoved (HttpSessionBindingEvent  hsbe)	当删除 HttpSession 对象中的一个属性时
 * public void attributeReplaced (HttpSessionBindingEvent  hsbe) 	当 HttpSession 对象中的某个属性被替换时
 * HttpServletRequest	ServletRequestAttributeListener	用于监听 HttpServletRequest 对象的属性新增、移除和替换	public void attributeAdded (ServletRequestAttributeEvent srae)	当 HttpServletRequest 对象中新增一个属性时
 * public void attributeRemoved (ServletRequestAttributeEvent srae)	当删除 HttpServletRequest 对象中的一个属性时
 * public void attributeReplaced (ServletRequestAttributeEvent srae)	当 HttpServletRequest 对象中的某个属性被替换时
 *
 * 监听 Session 中对象状态改变的监听器
 *  Session 中的对象可以有多种状态：绑定到 Session 中、从 Session 中解除绑定、随 Session 对象持久化到存储设备中(钝化)、随 Session 对象从存储设备中恢复（活化）。
 * Servlet 规范中定义了两个特殊的监听器接口，用来帮助对象了解自己在 Session 中的状态：
 * HttpSessionBindingListener 接口和 HttpSessionActivationListener 接口 ，实现这两个接口的类不需要进行注册。
 * 事件源	                监听器	                监听器描述	            方法	                调用时机
 * HttpSession	HttpSessionBindingListener	用于监听 JavaBean 对象绑定到 HttpSession 对象和从 HttpSession 对象解绑的事件	void  valueBound (HttpSessionBindingEvent event)	当对象被绑定（添加）到 HttpSession 对象中时
 * void  valueUnbound (HttpSessionBindingEvent event)	当对象从 HttpSession 对象中解除绑定（移除）时
 * HttpSessionActivationListener	用于监听 HttpSession 中对象活化和钝化的过程	void sessionWillPassivate (HttpSessionBindingEvent event)	当绑定到 HttpSession 对象中的对象将要随 HttpSession 对象被钝化之前
 * void  sessionDidActive (HttpSessionBindingEvent event)	当绑定到 HttpSession 对象中的对象将要随 HttpSession 对象被活化之后
 *
 * 注册监听器
 * 注册 Servlet 监听器有 2 种方式，分别是：
 * 在 web.xml 中注册监听器；
 * 使用 @WebListener 注册监听器。
 */

@WebListener
public class Listener1 implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener
{

    public Listener1()
    {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    @Override
    public void sessionCreated(HttpSessionEvent se)
    {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se)
    {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe)
    {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe)
    {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe)
    {
        /* This method is called when an attribute is replaced in a session. */
    }
}
