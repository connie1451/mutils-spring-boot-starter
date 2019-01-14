mutils-spring-boot-starter 是一款基于JDK8,SpringBoot 2.0.0 构建的工具类.
统一集成的功能如下：
 - 支付宝支付
 - 微信支付(小程序、app、web)
 - 微信小程序
 - 点我达(跑腿服务)
 - 移客通(虚拟号码服务)
 - excel(自主研发对excel的快捷导入导出)
 - 快递100(快递服务查询)
 - file(上传文件路径提供便捷配置)
 
 
 1.mutils-core中的web包内提供VO(极大的减少了创建实体类的工作量)、Result、Page等web服务常见的包装类.<br/>
    tools中提供了日常使用的帮助类,如Stringutil、NumberUtil、DateUtil等等,可继承额外拓展.<br/>
 2.file生成的规则是xxx-副本,如果有相同文件则会生成xxx-副本(序号)<br/>
 3.基于springboot，开发者可以简单的在yml或properties中简单的配置(在配置文件中出现提示)，更快的上手！<br/>
 4.后期我们会完善注释为中英文双版，让对英语不熟悉的人也能够快速的找到问题所在！<br/>
   我们将会持续更新。<br/>

<h3>为什么会有这样的一个开源项目的诞生？</h3>
 	相信大部分开发者都使用过x信支付,这个项目的API确实方便了很多人,让用户体验到了x信支付的快捷;<br/>
	但是对于开发人员来说,x信支付的API何尝不然人头疼，可以在百度找到一大堆，其中不乏签名失败，商户与appid不匹配等。<br/>
	还有很多这样的例子,它的诞生是的原因就是为了解决这样的问题。<br/>
	让一个刚接触某个API的人也能够花少量时间，能够花更少的时间完成,有更多的时间去做其他的事情。<br/>
	
	当然,这绝对不是一件简单的事情。我们希望有更多的人加入，让这套mutils框架更加的完善、更加的便捷、更加的实用！
	
Version 0.2.0 更新内容:<br/><br/>

1.将微信支付拆分为 微信-小程序、微信-app、微信-jsapi 分别提供对应模块特有功能。微信支付将集成在他们公共对应的Functions中<br/>
例：
微信-app：WechatAppFunctions.createAppPayParamter(model);//发起支付
微信-小程序：WechatMiniProgramFunctions.createMiniProgramPayParamter(model)//发起支付
可利用maven进行动态选择

2.文件上传新增远程上传：
	详细请看mutils说明文档

	
	
