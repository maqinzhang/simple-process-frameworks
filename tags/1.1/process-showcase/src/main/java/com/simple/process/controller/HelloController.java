/**
 * 
 */
package com.simple.process.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simple.frameworks.processframework.invoke.ProcessInvoker;
import com.simple.frameworks.processframework.result.Result;

/**
 * @author luolishu
 * 
 */
@Controller
@RequestMapping
public class HelloController {
	@Resource
	ProcessInvoker processInvoker;

	@RequestMapping("/hello")
	public String hello(Model model) {
		Result result=processInvoker.invoke(null);
		model.addAttribute("message", result.getModel());
		return "hello";
	}
}
