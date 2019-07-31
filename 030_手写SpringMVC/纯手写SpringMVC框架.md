#### 纯手写SpringMVC框架

#### 一、创建maven项目

*带webapp目录的maven项目*

##### 1.pom.xml

```xml
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
    </dependency>
    <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>commons-lang3</artifactId>
      <version>3.0</version>
    </dependency>
```

#### 二、创建DispatcherServlet

> 在[SpringMVC源码分析之DispatcherServlet](https://blog.csdn.net/weixin_40826349/article/details/96998510)分析中，我们可以知道DispatcherServlet继承了FrameworkServlet，又继承了HttpServletBean，又继承了HttpServlet，因此我们需要创建HttpServletBean、FrameworkServlet、DispatcherServlet四个类。
>
> ![](https://img-blog.csdnimg.cn/20190723133957959.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MDgyNjM0OQ==,size_16,color_FFFFFF,t_70)

##### 1.创建HttpServletBean

创建HttpServletBean，继承HttpServlet，并重写父类init方法，调用initServletBean方法

```java
public class HttpServletBean extends HttpServlet {
    @Override
    public void init() throws ServletException {
        initServletBean();
    }

    private void initServletBean() {
        
    }
}
```

##### 2.创建FrameworkServlet

创建FrameworkServlet，重写initServletBean方法，并调用onRefresh方法；

重写父类service方法，调用doService方法。

```java
public class FrameworkServlet extends  HttpServletBean{
    @Override
    protected void initServletBean() {
        onRefresh();
    }

    protected void onRefresh() {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        doService( req,  resp);
    }

    protected void doService(HttpServletRequest req, HttpServletResponse resp) {

    }
}
```

##### 3.创建DispatcherServlet

创建DispatcherServlet，继承FrameworkServlet，重写父类onRefresh方法，调用initStrategies方法，再调用initHandlerMappings方法，在initHandlerMappings方法中，我们需要获取controller注解扫包的范围，通过RequestMappingInfoHandlerMapping的registryMapping方法；重写父类doService方法。

```java
public class DispatcherServlet extends FrameworkServlet {

    @Override
    protected void onRefresh() {
        initStrategies();
    }

    private void initStrategies() {
        initHandlerMappings();
    }

    private void initHandlerMappings() {
    }

    @Override
    protected void doService(HttpServletRequest req, HttpServletResponse resp) {
    }
}
```

#### 三、SpringMVC对象的初始化

##### 1.定义ComponentScan、Controller、RequestMapping注解类

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ComponentScan {

    String value() default "";
}
```

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String value() default "";
}
```

```java
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String value() default "";
}
```

##### 2.创建HandlerMethod

```java
public class HandlerMethod {

    // 请求方法对应的bean对象
    private Object bean;
    private Method method;

    public HandlerMethod(Object bean, Method method) {
        this.bean = bean;
        this.method = method;
    }

    public Object getBean() {
        return bean;
    }

    public Method getMethod() {
        return method;
    }
}
```

##### 3.创建SpringMvcConfig和IndexController

```java
@ComponentScan("com.wmh.controller")
public class SpringMvcConfig {
}
```

```java
@Controller
public class IndexController {
    @RequestMapping(value = "/")
    public String index1(){
        return "index";
    }
}
```

##### 4.创建反射工具类

```java
package com.wmh.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ReflexUtils {

    /**
     * 从包package中获取所有的Class
     *
     * @param pack
     * @return
     */
    public static Set<Class<?>> getClasses(String pack) {

        // 第一个class类的集合
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字 并进行替换
        String packageName = pack;
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(
                    packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    System.err.println("file类型的扫描");
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath,
                            recursive, classes);
                } else if ("jar".equals(protocol)) {
                    // 如果是jar包文件
                    // 定义一个JarFile
                    System.err.println("jar类型的扫描");
                    JarFile jar;
                    try {
                        // 获取jar
                        jar = ((JarURLConnection) url.openConnection())
                                .getJarFile();
                        // 从此jar包 得到一个枚举类
                        Enumeration<JarEntry> entries = jar.entries();
                        // 同样的进行循环迭代
                        while (entries.hasMoreElements()) {
                            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            // 如果是以/开头的
                            if (name.charAt(0) == '/') {
                                // 获取后面的字符串
                                name = name.substring(1);
                            }
                            // 如果前半部分和定义的包名相同
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                // 如果以"/"结尾 是一个包
                                if (idx != -1) {
                                    // 获取包名 把"/"替换成"."
                                    packageName = name.substring(0, idx)
                                            .replace('/', '.');
                                }
                                // 如果可以迭代下去 并且是一个包
                                if ((idx != -1) || recursive) {
                                    // 如果是一个.class文件 而且不是目录
                                    if (name.endsWith(".class")
                                            && !entry.isDirectory()) {
                                        // 去掉后面的".class" 获取真正的类名
                                        String className = name.substring(
                                                packageName.length() + 1, name
                                                        .length() - 6);
                                        try {
                                            // 添加到classes
                                            classes.add(Class
                                                    .forName(packageName + '.'
                                                            + className));
                                        } catch (ClassNotFoundException e) {
                                            // log
                                            // .error("添加用户自定义视图类错误 找不到此类的.class文件");
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        // log.error("在扫描用户定义视图时从jar包获取文件出错");
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }
    /**
     * 以文件的形式来获取包下的所有Class
     *
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    public static void findAndAddClassesInPackageByFile(String packageName,
                                                        String packagePath, final boolean recursive, Set<Class<?>> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(packagePath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            // log.warn("用户定义包名 " + packageName + " 下没有任何文件");
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            public boolean accept(File file) {
                return (recursive && file.isDirectory())
                        || (file.getName().endsWith(".class"));
            }
        });
        // 循环所有文件
        for (File file : dirfiles) {
            // 如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "."
                                + file.getName(), file.getAbsolutePath(), recursive,
                        classes);
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0,
                        file.getName().length() - 6);
                try {
                    // 添加到集合中去
                    //classes.add(Class.forName(packageName + '.' + className));
                    //经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    // log.error("添加用户自定义视图类错误 找不到此类的.class文件");
                    e.printStackTrace();
                }
            }
        }
    }
}
```

##### 5.创建RequestMappingInfoHandlerMapping

```java
public class RequestMappingInfoHandlerMapping {
    private final Map<String, HandlerMethod> registryMapping = new HashMap<String, HandlerMethod>();

    public void registryMapping() {
        //1.获取SpringMVConfig的ComponentScan扫包范围
        ComponentScan declaredAnnotation = SpringMvcConfig.class.getDeclaredAnnotation(ComponentScan.class);
        if (declaredAnnotation == null) {
            return;
        }
        //2.获取ComponentScan中value
        String springmvcPackage = declaredAnnotation.value();
        if (StringUtils.isEmpty(springmvcPackage)) {
            return;
        }
        //3.使用Java反射机制获取扫包范围中的类
        Set<Class<?>> classes = ReflexUtils.getClasses(springmvcPackage);
        //4.遍历每个类，查找类中的方法是否有加上Controller注解
        classes.stream().forEach(item -> {
            Controller controller = item.getDeclaredAnnotation(Controller.class);
            if (controller == null) {
                return;
            }
            //5.获取类中的所有方法
            Method[] declaredMethods = item.getDeclaredMethods();
            for (Method method : declaredMethods) {
                //6.遍历方法，查找方法上是否加上RequestMapping注解
                RequestMapping requestMapping = method.getDeclaredAnnotation(RequestMapping.class);
                if (requestMapping != null) {
                    //7.获取requestMapping中的value
                    String url = requestMapping.value();
                    //8.添加到registryMapping中
                    registryMapping.put(url, new HandlerMethod(newInstance(item), method));
                }
            }
        });
    }

    public HandlerMethod getHandler(String url) {
        return registryMapping.get(url);
    }

    private Object newInstance(Class classInfo) {
        try {
            Object value = classInfo.newInstance();
            return value;
        } catch (Exception e) {
            return null;
        }
    }
}
```

#### 四、编写DispatcherServlet中doService方法

##### 1.创建HandlerExecutionChain

```java
public class HandlerExecutionChain {
    HandlerMethod handlerMethod;

    public HandlerExecutionChain(HandlerMethod handlerMethod) {
        this.handlerMethod = handlerMethod;
    }

    public ModelAndView handler() throws InvocationTargetException, IllegalAccessException {
        // 1. 使用java的反射机制执行我们请求方法
        Method method = handlerMethod.getMethod();
        Object bean = handlerMethod.getBean();
        // 2.执行我们的请求的方法
        Object viewName = method.invoke(bean, null);
        ModelAndView modelAndView = new ModelAndView((String) viewName);
        return modelAndView;
    }
}
```

##### 2.创建ModelAndView

```java
public class ModelAndView {
    // 跳转页面名称
    private String viewName;

    public ModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }
}
```

##### 3.编写DispatcherServlet中doService方法

```java
public class DispatcherServlet extends FrameworkServlet {
    RequestMappingInfoHandlerMapping requestMappingInfoHandlerMapping;

    public DispatcherServlet() {
        requestMappingInfoHandlerMapping = new RequestMappingInfoHandlerMapping();
    }

    @Override
    protected void onRefresh() {
        initStrategies();
    }

    private void initStrategies() {
        initHandlerMappings();
    }

    private void initHandlerMappings() {
        System.out.println("<<<<初始化initHandlerMappings>>>>");
        requestMappingInfoHandlerMapping.registryMapping();
    }

    @Override
    protected void doService(HttpServletRequest req, HttpServletResponse resp) {
        //1.获取Url请求
        String requestURI = req.getRequestURI();
        //2.根据url请求获取具体的handler
        HandlerExecutionChain handler = getHandler(requestURI);
        try {
            if (handler == null) {
                noHandlerFound(req, resp);
                return;
            }
            //3.执行handler方法
            ModelAndView mv = handler.handler();
            //4.渲染页面
            render(mv, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 根据modelAndView渲染页面
     * @param mv
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void render(ModelAndView mv, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewName = mv.getViewName();
        req.getRequestDispatcher("/WEB-INF/view/" + viewName + ".jsp").forward(req, resp);//同时创建index.jsp
    }


    private void noHandlerFound(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        resp.getWriter().print("没有查找到该请求");
    }


    /***
     * 根据url在HandlerMapping中获取具体的类，然后获取集具体点的Handler
     * @param url
     * @return
     */
    public HandlerExecutionChain getHandler(String url) {
        HandlerMethod handler = requestMappingInfoHandlerMapping.getHandler(url);
        if (handler == null)
            return null;
        HandlerExecutionChain handlerExecutionChain = new HandlerExecutionChain(handler);
        return handlerExecutionChain;
    }
}
```

##### 4.在webapp/WEB-INF/view在创建index.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
手写SpringMVC成功
</body>
</html>
```

#### 五、创建SpringServletContainerInitializer容器初始化

##### 1.创建WebApplicationInitializer

```java
public class WebApplicationInitializer {
}
```

##### 2.创建AbstractDispatcherServletInitializer，继承WebApplicationInitializer，编写onStartup方法

```java
public class AbstractDispatcherServletInitializer extends WebApplicationInitializer {
    public void onStartup(ServletContext ctx)throws ServletException{
        ServletRegistration.Dynamic dynamic=ctx.addServlet("dispatcherServlet",new DispatcherServlet());
        dynamic.addMapping("/");//拦截所有请求
    }
}
```

##### 3.创建SpringServletContainerInitializer

```java
@HandlesTypes(WebApplicationInitializer.class)
public class SpringServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        for (Class<?> classInfo : set) {
            try {
                //使用Java反射技术执行onStartup方法
                Object o = classInfo.newInstance();
                Method onStartup = classInfo.getMethod("onStartup", ServletContext.class);
                onStartup.invoke(o, servletContext);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
```

##### 4.在resources目录下，创建META-INF/services/javax.servlet.ServletContainerInitializer

```
com.wmh.servlet.web.SpringServletContainerInitializer
```





