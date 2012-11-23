/**
 * 
 */
package com.jd.frameworks.processframework.invoke;

import java.util.List;

import com.jd.frameworks.processframework.ExecuteContext;
import com.jd.frameworks.processframework.ProcessContext;
import com.jd.frameworks.processframework.config.DefaultConfigurationManager;
import com.jd.frameworks.processframework.converter.InjectTypeConverter;
import com.jd.frameworks.processframework.exception.ExceptionHandler;
import com.jd.frameworks.processframework.request.Request;
import com.jd.frameworks.processframework.request.Session;
import com.jd.frameworks.processframework.result.DefaultResult;
import com.jd.frameworks.processframework.result.Result;

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
		} finally {
			ProcessContext.setContext(null);
		}
		return result;
	}

	protected Result<?, ?> doInvoke(Request request) {
		Object value = null;
		DelegatingExecuteContext delegatingContext = null;
		ProcessContext context = new ProcessContext();
		ProcessContext.setContext(context);
		try {
			delegatingContext = createContext(request);
			SubProcess mainProcess = processHolder.getMainProcess();
			value = this.invokeMainProcess(mainProcess, delegatingContext);
			if (value != null && value instanceof Result) {
				return (Result) value;
			}
		} catch (Throwable e) {
			SubProcess exceptionProcess = processHolder.getExceptionProcess(e
					.getClass());
			value = this
					.invokeCatchProcess(exceptionProcess, delegatingContext);
		} finally {
			SubProcess finalProcess = processHolder.getMainProcess();
			value = this.invokeFinallyProcess(finalProcess, delegatingContext);
		}
		if (value != null && value instanceof Result) {
			return (Result) value;
		}
		return delegatingContext.result;
	}

	protected DelegatingExecuteContext createContext(Request request)
			throws Exception {
		DelegatingExecuteContext delegatingContext = null;
		Session session = sessionFactory.createSession();
		ExecuteContext context = executeContextFactory.create(request, session);
		Result result = createResult(context);
		context.result = result;
		delegatingContext = new DelegatingExecuteContext(context);
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

	protected Object invokeMainProcess(SubProcess subProcess,
			DelegatingExecuteContext context) throws Exception {
		Object result = null;
		context.lastSubProcess = subProcess;
		List<ExecutionNode> nodes = subProcess.getNodes();
		if (nodes == null) {
			return result;
		}
		for (ExecutionNode node : nodes) {
			context.lastExecuteNode = node;
			if (node.validate(context)) {
				result = node.execute(context);
				if (result != null && result instanceof Result) {
					return result;
				}
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	protected Object invokeCatchProcess(SubProcess subProcess,
			DelegatingExecuteContext context) {
		Object result = null;
		try {
			context.lastSubProcess = subProcess;
			List<ExecutionNode> nodes = subProcess.getNodes();
			if (nodes == null) {
				return result;
			}
			for (ExecutionNode node : nodes) {
				context.lastExecuteNode = node;
				if (node.validate(context)) {
					result = node.execute(context);
					if (result != null && result instanceof Result) {
						return result;
					}
				}
			}
		} catch (Exception e) {
			result = exceptionHandler.handle(e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	protected Object invokeFinallyProcess(SubProcess subProcess,
			DelegatingExecuteContext context) {
		Object result = null;
		try {
			context.lastSubProcess = subProcess;
			List<ExecutionNode> nodes = subProcess.getNodes();
			if (nodes == null) {
				return null;
			}
			for (ExecutionNode node : nodes) {
				context.lastExecuteNode = node;
				if (node.validate(context)) {
					result = node.execute(context);
					if (result != null && result instanceof Result) {
						return result;
					}
				}
			}
		} catch (Exception e) {
			result = exceptionHandler.handle(e);
		}
		return result;
	}

	public void setProcessHolder(ProcessHolder processHolder) {
		this.processHolder = processHolder;
	}

	public void setExceptionHandler(ExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

}
