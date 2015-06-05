QQ交流群74914061

### 框架简介 ###
在SOA的服务化治理中，我们的目的就是让让业务更加清晰明了，各个系统之间的交互更加简单，分而治之，数据共享更加方便和集中。<br />
所以我们采用接口的方式进行编程，采用RPC的方式进行实时的跨系统调用，采用异步消息系统分割各个应用和数据同步，采用领域建模来驱动业务设计。<br />
从而在整个应用系统高度看，它是一个分布式的系统。在业界的商业产品里，我们可以看到一套套的SOA解决方案，ESB，WebService，MQ，规则引擎，BPMN等等。<br />
而商业的产品对于互联网企业来说，一方面需要耗费较高的授权费用，而且整个系统学习成本和系统复杂度比较高，对于我们来说不可控。<br />
simple-process-frameworks是一套简单的流程业务处理框架，用于解决在开发过程中业务存在着流水作业方式的场景，以及需要进行业务模块切分的问题。<br />
在日常开发中，很多业务功能是按照流的方式进行处理的，而在业务处理过程中，可以抽取成若干个公共的逻辑单元，以组件单元的方式进行配置，应用于不同的流程。<br />

### 本项目的目标： ###
  1. 降低维护成本
  1. 提高开发效率
  1. 建立开发规范和标准
  1. 服务化集成支持
  1. 业务规则组件化，设计驱动开发
  1. 方便线上动态发布和管理
  1. 测试支持
  1. 文档化支持
### 已经完成了的功能 ###
  1. 流程执行和控制（可用于管道方式的代码编写）
  1. 与Spring配置集成
  1. 支持动态语言脚本的执行(javascript等)
  1. 支持try...catch...
  1. 动态参数绑定注入
  1. 流程自动代理成接口的方式调用

### 待开发的功能 ###
  1. 流程界面跟踪
  1. 后台管理配置程序（提供发布和配置管理功能）
  1. 后台监控程序
  1. 文档化生成
  1. 测试支持功能


### 与Spring集成 ###
```
<?xml version="1.0" encoding="GBK"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:util="http://www.springframework.org/schema/util" xmlns:context="http://www.springframework.org/schema/context"
	xmlns:process="http://www.opensource.com/schema/process"
	xsi:schemaLocation="
     http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
     http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
     http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc-3.0.xsd
     http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
     http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
     http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-3.1.xsd
     http://www.opensource.com/schema/process  http://www.opensource.com/schema/process/process-3.0.xsd"
	default-autowire="byName"> 
	<context:annotation-config />
	<context:component-scan base-package="com.simple.process" />
	
	<!-- <bean
		class="com.jd.frameworks.processframework.spring.config.ProcessLoaderScannerConfigurer">
		<property name="locations" value="classpath:process-*-sample.xml" />

	</bean> --> 
	
	<process:process-config locations="classpath:/process/process-showcase.xml"/>

</beans>
配置上提供两种配置方式，一种是基于命名空间的方式，一种基于Bean的Scanner的方式
```
### 流程配置文件内容 ###
```
<?xml version="1.0" encoding="UTF-8"?>
<processes>
	<!-- 流程配置，ID可以再Spring中直接getBean得到，流程实现了HelloWorldService接口 -->
	<process id="helloServiceInvoker" interface="com.simple.process.service.HelloWorldService">
		<node id="processStart" />
		<!-- 提供条件判断逻辑控制 -->
		<if test="request.getParameter('name')=='xiaoming'">
			<node id="sayHelloToXiaoMing" />
		</if>
		<elseif test="request.getParameter('name')=='xiaohua'">
			<node id="sayHelloToXiaoHua" />
		</elseif>
		<else>
			<node id="sayHelloWorld" />
		</else>
		<node id="exceptionShowStart" />
                <!-- Try...Catch...支持 -->
		<try>
			<node id="tryNode" />
			<node id="throwExceptionNode" />
		</try>
		<catch exception="com.simple.process.exception.SampleException">
			<node id="exceptionHanlder" />
		</catch>
		<finally>
			<node id="finallyHandler" />
		</finally>
		<node id="exceptionShowEnd" />
		<node id="moreComplexShowStart" />
		<if test="request.getParameter('age')>20">
			<if test="request.getParameter('age')>30">
				<node id="ageBiggerThan30" />
			</if>
			<else>
				<node id="ageBetween20And30" />
			</else>
			<try>
				<node id="sayHelloWorld" />
				<if test="request.getParameter('age')>100">
					<node id="throwExceptionNode" />
				</if>
			</try>
			<catch exception="com.simple.process.exception.SampleException">
				<node id="exceptionHanlder" />
			</catch>
		</if>
		<node id="moreComplexShowEnd" />
	</process>
</processes>
```

### 代码实现示例1： ###
```
* validate为默认校验方法
  1.返回true则执行execute方法，否则不执行
  2.返回Result或者throw Exception则直接结束当前流程的执行(假如没有在try...catch中)
* execute为默认的执行方法
  1.返回Result或者throw Exception则直接结束当前流程的执行(假如没有在try...catch中)
public class ValidationShow {

	public boolean validate() {
		return true;
	}

	public void execute() {
		System.out.println("validation default.");
	}
}
```
### 代码实现示例2(注解的方式)： ###
```
* validate为默认校验方法
  1.返回true则执行execute方法，否则不执行
  2.返回Result或者throw Exception则直接结束当前流程的执行(假如没有在try...catch中)
* execute为默认的执行方法
  1.返回Result或者throw Exception则直接结束当前流程的执行(假如没有在try...catch中)
public class ValidationAnnotaiontShow {
	/**
	 * use @Validate annotation to defined a validate method
	 * 
	 * @param name
	 * @return
	 */
	@Validate
	public boolean method1(@RequestVar("name") String name) {
		return true;
	}
	/**
	 * use @Execute annotation to defined a execute method
	 * 
	 * @param name
	 * @return
	 */
	@Execute
	public void method2() {
		System.out.println("use annotation support");
	}
}
```
欢迎大家加入交流和讨论