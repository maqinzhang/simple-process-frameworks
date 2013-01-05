package com.simple.frameworks.javassist;

import java.lang.reflect.Method;  

import javassist.CannotCompileException;  
import javassist.ClassPool;  
import javassist.CtClass;  
import javassist.NotFoundException;  
import javassist.CtMethod;  
import javassist.CtNewMethod;  
  
/** 
 * ����javassistʵ�ֵĶ�̬�����࣬��������ʱ���ڴ��ж�̬���Ҫ����Ľӿڵ�ʵ�֣����ڽӿڵķ���ʵ���з��� 
 * com.cuishen.myAop.InterceptorHandler��9����ӿڣ���invoke������������ʹ�ñ�����֮ǰ����ʵ�� 
 * 9����ӿڡ��������ṩ�ӽ�java.lang.reflect.Proxy�Ĺ��� 
 * @author cuishen 
 * @version 1.0 
 * @see com.cuishen.myAop.InterceptorHandler 
 * @see java.lang.reflect.Proxy 
 */  
public class JavassistProcessInvoker {  
    /** ��̬������������׺ */  
    private final static String PROXY_CLASS_NAME_SUFFIX = "$MyProxy_";  
    /** 9����ӿ� */  
    private final static String INTERCEPTOR_HANDLER_INTERFACE = "com.cuishen.myAop.InterceptorHandler";  
    /** ��̬����������������ֹ�����ظ� */  
    private static int proxyClassIndex = 1;  
      
    /** 
     * ��¶���û��Ķ�̬����ӿڣ�����ĳ��ӿڵĶ�̬�������ע�Ȿ����ʵ�����com.cuishen.myAop.InterceptorHandler9������� 
     * ʹ�ã����û�Ҫʹ�ñ���̬���?����ʵ��com.cuishen.myAop.InterceptorHandler9����ӿ� 
     * <br> 
     * ʹ�÷�������: 
     * <br> 
     * <code> 
     * StudentInfoService studentInfo = (StudentInfoService)MyProxyImpl.newProxyInstance(String, String, String); 
     * <br>studentInfo.��������; 
     * </code> 
     * @param interfaceClassName String Ҫ��̬����Ľӿ�����, e.g test.StudentInfoService 
     * @param classToProxy String Ҫ��̬����Ľӿڵ�ʵ���������, e.g test.StudentInfoServiceImpl 
     * @param interceptorHandlerImplClassName String �û��ṩ��9����ӿڵ�ʵ��������� 
     * @return Object ����ĳ��ӿڵĶ�̬������� 
     * @throws InstantiationException 
     * @throws IllegalAccessException 
     * @throws NotFoundException 
     * @throws CannotCompileException 
     * @throws ClassNotFoundException 
     * @see com.cuishen.myAop.InterceptorHandler 
     */  
    public static Object newProxyInstance(String interfaceClassName, String classToProxy, String interceptorHandlerImplClassName) throws InstantiationException, IllegalAccessException, NotFoundException, CannotCompileException, ClassNotFoundException {  
        Class interfaceClass = Class.forName(interfaceClassName);  
        Class interceptorHandlerImplClass = Class.forName(interceptorHandlerImplClassName);  
        return dynamicImplementsInterface(classToProxy, interfaceClass, interceptorHandlerImplClass);  
    }  
      
    /** 
     * ��̬ʵ��Ҫ����Ľӿ� 
     * @param classToProxy String Ҫ��̬����Ľӿڵ�ʵ���������, e.g test.StudentInfoServiceImpl 
     * @param interfaceClass Class Ҫ��̬����Ľӿ���, e.g test.StudentInfoService 
     * @param interceptorHandlerImplClass Class �û��ṩ��9����ӿڵ�ʵ���� 
     * @return Object ����ĳ��ӿڵĶ�̬������� 
     * @throws NotFoundException 
     * @throws CannotCompileException 
     * @throws InstantiationException 
     * @throws IllegalAccessException 
     */  
    private static Object dynamicImplementsInterface(String classToProxy, Class interfaceClass, Class interceptorHandlerImplClass) throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException {  
        ClassPool cp = ClassPool.getDefault();  
        String interfaceName = interfaceClass.getName();  
        //��ָ̬�������������  
        String proxyClassName = interfaceName + PROXY_CLASS_NAME_SUFFIX + proxyClassIndex++;   
          
        CtClass ctInterface = cp.get(interfaceName);
        CtClass cc = cp.makeClass(proxyClassName);  
        cc.addInterface(ctInterface);   
        Method [] methods = interfaceClass.getMethods();  
        for(int i = 0; i < methods.length; i++) {  
            Method method = methods[i];  
            dynamicImplementsMethodsFromInterface(classToProxy, cc, method, interceptorHandlerImplClass, i);  
        }  
        return (Object)cc.toClass().newInstance();  
    }  
      
    /** 
     * ��̬ʵ�ֽӿ���ķ��� 
     * @param classToProxy String Ҫ��̬����Ľӿڵ�ʵ���������, e.g test.StudentInfoServiceImpl 
     * @param implementer CtClass ��̬������İ�װ 
     * @param methodToImpl Method ��̬����������Ҫʵ�ֵĽӿڷ����İ�װ 
     * @param interceptorClass Class �û��ṩ��9����ʵ���� 
     * @param methodIndex int Ҫʵ�ֵķ��������� 
     * @throws CannotCompileException 
     */  
    private static void dynamicImplementsMethodsFromInterface(String classToProxy, CtClass implementer, Method methodToImpl, Class interceptorClass, int methodIndex) throws CannotCompileException {  
        String methodCode = generateMethodCode(classToProxy, methodToImpl, interceptorClass, methodIndex);  
        CtMethod cm = CtNewMethod.make(methodCode, implementer);  
        implementer.addMethod(cm);  
    }  
      
    /** 
     * ��̬��װ�����壬��Ȼ��������ķ���ʵ�ֲ����Ǽ򵥵ķ������������Ƿ��������9�������invoke�������������յ��Ĳ�����д��� 
     * @param classToProxy String Ҫ��̬����Ľӿڵ�ʵ���������, e.g test.StudentInfoServiceImpl 
     * @param methodToImpl Method ��̬����������Ҫʵ�ֵĽӿڷ����İ�װ 
     * @param interceptorClass Class �û��ṩ��9����ʵ���� 
     * @param methodIndex int Ҫʵ�ֵķ��������� 
     * @return String ��̬��װ�ķ������ַ� 
     */  
    private static String generateMethodCode(String classToProxy, Method methodToImpl, Class interceptorClass, int methodIndex) {  
        String methodName = methodToImpl.getName();  
        String methodReturnType = methodToImpl.getReturnType().getName();  
        Class []parameters = methodToImpl.getParameterTypes();  
        Class []exceptionTypes = methodToImpl.getExceptionTypes();  
        StringBuffer exceptionBuffer = new StringBuffer();  
        //��װ������Exception����  
        if(exceptionTypes.length > 0) exceptionBuffer.append(" throws ");  
        for(int i = 0; i < exceptionTypes.length; i++) {  
            if(i != exceptionTypes.length - 1) exceptionBuffer.append(exceptionTypes[i].getName()).append(",");  
            else exceptionBuffer.append(exceptionTypes[i].getName());  
        }  
        StringBuffer parameterBuffer = new StringBuffer();  
        //��װ�����Ĳ����б�  
        for(int i = 0; i < parameters.length; i++) {  
            Class parameter = parameters[i];  
            String parameterType = parameter.getName();  
            //��ָ̬����������ı���  
            String refName = "a" + i;  
            if(i != parameters.length - 1) parameterBuffer.append(parameterType).append(" " + refName).append(",");  
            else parameterBuffer.append(parameterType).append(" " + refName);  
        }  
        StringBuffer methodDeclare = new StringBuffer();  
        //��������������ʵ�ֽӿڵķ�����������public  
        methodDeclare.append("public ").append(methodReturnType).append(" ").append(methodName).append("(").append(parameterBuffer).append(")").append(exceptionBuffer).append(" {\n");  
        String interceptorImplName = interceptorClass.getName();  
        //������  
        methodDeclare.append(INTERCEPTOR_HANDLER_INTERFACE).append(" interceptor = new ").append(interceptorImplName).append("();\n");  
        //��������û���9����ӿ�  
        methodDeclare.append("Object returnObj = interceptor.invoke(Class.forName(\"" + classToProxy + "\").newInstance(), Class.forName(\"" + classToProxy + "\").getMethods()[" + methodIndex + "], ");  
        //���ݷ�����Ĳ���  
        if(parameters.length > 0) methodDeclare.append("new Object[]{");   
        for(int i = 0; i < parameters.length; i++) {  
            //($w) converts from a primitive type to the corresponding wrapper type: e.g.  
            //Integer i = ($w)5;  
            if(i != parameters.length - 1) methodDeclare.append("($w)a" + i + ",");  
            else methodDeclare.append("($w)a" + i);  
        }  
        if(parameters.length > 0) methodDeclare.append("});\n");  
        else methodDeclare.append("null);\n");  
        //�Ե���9����ķ���ֵ���а�װ  
        if(methodToImpl.getReturnType().isPrimitive()) {  
            if(methodToImpl.getReturnType().equals(Boolean.TYPE)) methodDeclare.append("return ((Boolean)returnObj).booleanValue();\n");  
            else if(methodToImpl.getReturnType().equals(Integer.TYPE)) methodDeclare.append("return ((Integer)returnObj).intValue();\n");  
            else if(methodToImpl.getReturnType().equals(Long.TYPE)) methodDeclare.append("return ((Long)returnObj).longValue();\n");  
            else if(methodToImpl.getReturnType().equals(Float.TYPE)) methodDeclare.append("return ((Float)returnObj).floatValue();\n");  
            else if(methodToImpl.getReturnType().equals(Double.TYPE)) methodDeclare.append("return ((Double)returnObj).doubleValue();\n");  
            else if(methodToImpl.getReturnType().equals(Character.TYPE)) methodDeclare.append("return ((Character)returnObj).charValue();\n");  
            else if(methodToImpl.getReturnType().equals(Byte.TYPE)) methodDeclare.append("return ((Byte)returnObj).byteValue();\n");  
            else if(methodToImpl.getReturnType().equals(Short.TYPE)) methodDeclare.append("return ((Short)returnObj).shortValue();\n");  
        } else {  
            methodDeclare.append("return (" + methodReturnType + ")returnObj;\n");  
        }  
        methodDeclare.append("}");  
        System.out.println(methodDeclare.toString());  
        return methodDeclare.toString();  
    }  
      
}  