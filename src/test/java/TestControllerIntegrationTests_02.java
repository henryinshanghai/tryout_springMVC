import com.henry.springMVC4.customize_springmvc_02.MyMVCConfig_for_more_custom_demand_01;
import com.henry.springMVC4.customize_springmvc_02.springmvcTest_06.DemoService_01;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyMVCConfig_for_more_custom_demand_01.class})
//1 作用：声明所加载的上下文(容器)是一个Web上下文； 	用法：注解在类名上；
// 属性表示 Web资源的位置 - 默认值为 src/main/webapp, 这里自定义为 src/main/resources
@WebAppConfiguration("src/main/resources")
public class TestControllerIntegrationTests_02 {

	private MockMvc mockMvc; //2 模拟Mvc对象
	
	@Autowired
	private DemoService_01 demoService;//3 注入Spring容器中的Bean实例
	
	@Autowired 
	WebApplicationContext wac; //4 注入web应用上下文实例
	
    @Autowired 
    MockHttpSession session; //5 注入 模拟的session对象 - 没有真正使用它
    
    @Autowired 
    MockHttpServletRequest request; //6
    
    @Before //7 定义 在测试开始前需要执行的操作
    public void setup() {
    	// 初始化mvc对象	- 手段：MockMvcBuilders.webAppContextSetup(this.wac).build()
    	mockMvc =
    			MockMvcBuilders.webAppContextSetup(this.wac).build(); //2
    	}
	
	@Test
	public void testNormalController() throws Exception {
		mockMvc.perform(get("/normal")) //8 向/normal路径发送 get 请求
				.andExpect(status().isOk())//9 预期 控制器返回的状态是200
				.andExpect(view().name("page"))//10 预期 view的名称为page
				.andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp"))//11 预期页面的真实路径 /WEB-INF/classes/views/page.jsp
				.andExpect(model().attribute("msg", demoService.saySomething()));//12 预期 model对象中的msg属性的值 为 saySomething()方法的返回值
				
	}
	
	@Test
	public void testRestController() throws Exception {
		mockMvc.perform(get("/testRest")) //13 模拟向路径 /testRest发起get请求
        .andExpect(status().isOk())
         .andExpect(content().contentType("text/plain;charset=UTF-8"))//14 预期 返回值的媒体类型为 text/plain;charset=UTF-8
        .andExpect(content().string(demoService.saySomething()));//15 预期 返回值的内容 为 saySomething()方法的返回值
	}

}
