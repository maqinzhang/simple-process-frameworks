/**
 * 
 */
package com.opensource.frameworks.processframework.invoke;

import com.opensource.frameworks.processframework.ExecuteContext;
import com.opensource.frameworks.processframework.ProcessContext;
import com.opensource.frameworks.processframework.config.DefaultConfigurationManager;
import com.opensource.frameworks.processframework.converter.InjectTypeConverter;
import com.opensource.frameworks.processframework.exception.ExceptionHandler;
import com.opensource.frameworks.processframework.exception.ProcessInvokeException;
import com.opensource.frameworks.processframework.invocation.Invocation;
import com.opensource.frameworks.processframework.request.Request;
import com.opensource.frameworks.processframework.request.Session;
import com.opensource.frameworks.processframework.result.DefaultResult;
import com.opensource.frameworks.processframework.result.Result;

/**
 * @author luolishu
 * 
 */
@SuppressWarnings("rawtypes")
public class DefaultProcessInvoker implements ProcessInvoker {
	protected ProcessHolder processHolder;
	protected ExceptionHandler exceptionHandler;
	protected SessionFactory sessionFactory = new DefaultSessionFactory();
	protected ExecuteContextFactory executeContextFactory = new DefaultExecuteContextFactory();
	protected InjectTypeConverter injectTypeConverter;
	protected Invocation invocation;

	public DefaultProcessInvoker() {
		this(null, null);
	}

	public DefaultProcessInvoker(ProcessHolder processHolder,
			ExceptionHandler exceptionHandler) {
		this.processHolder = processHolder;
		this.exceptionHandler = exceptionHandler;
		this.injectTypeConverter = DefaultConfigurationManager.getInstance()
				.getInjectTypeConverter();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jd.frameworks.processframework.invoke.ProcessInvoker#invoke()
	 */
	public Result<?, ?> invoke(Request request) {
		Result<?, ?> result = null;
		ProcessContext context = new ProcessContext();
		try {
			ProcessContext.setContext(context);
			result = this.doInvoke(request);
		} catch (Throwable e) {
			throw new ProcessInvokeException(
					"invoking process error!ProcessConfig="
							+ processHolder.getProcessConfig(), e);
		} finally {
			ProcessContext.setContext(null);
		}
		return result;
	}

	protected Result<?, ?> doInvoke(Request request) throws Throwable {
		Object value = null;
		WrapperExecuteContext wrapperContext = null;
		ProcessContext context = new ProcessContext();
		ProcessContext.setContext(context);
		try {
			wrapperContext = createContext(request);
			/*
			 * if(this.invocation==null){ synchronized(this){
			 * this.invocation=InvocationBuilder
			 * .build(this.processHolder.getProcessConfig()); } }
			 */
			value = invocation.invoke(wrapperContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (value != null && value instanceof Result) {
			return (Result) value;
		}
		return wrapperContext.result;
	}

	protected WrapperExecuteContext createContext(Request request)
			throws Exception {
		WrapperExecuteContext delegatingContext = null;
		Session session = sessionFactory.createSession();
		ExecuteContext context = executeContextFactory.create(request, session);
		Result result = createResult(context);
		context.result = result;
		delegatingContext = new WrapperExecuteContext(context);
		delegatingContext.processHolder = processHolder;
		return delegatingContext;
	}

	protected Result createResult(ExecuteContext context)
			throws InstantiationException, IllegalAccessException {
		Result result = null;
		if (processHolder.getResultClass() != null) {
			result = (Result) processHolder.getResultClass().newInstance();
		} else {
			result = new DefaultResult();
		}
		context.result = result;
		return result;
	}

	public void setInvocation(Invocation invocation) {
		this.invocation = invocation;
	}

	public void setProcessHolder(ProcessHolder processHolder) {
		this.processHolder = processHolder;
	}

	public void setExceptionHandler(ExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

}
