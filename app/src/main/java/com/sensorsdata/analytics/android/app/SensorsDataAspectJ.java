package com.sensorsdata.analytics.android.app;

import android.support.annotation.Keep;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Locale;

@Aspect
@Keep
@SuppressWarnings("all")
public class SensorsDataAspectJ {
    private static final String TAG = "SensorsDataAspectJ";

    @Around("execution(* *(..))")
    public Object weaveAllMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long startNanoTime = System.nanoTime();

        Object returnObject = joinPoint.proceed();

        //纳秒，1毫秒=1纳秒*1000*1000
        long stopNanoTime = System.nanoTime();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Log.i(TAG, String.format(Locale.CHINA, "Method:<%s> cost=%s ns", method.toGenericString(), String.valueOf(stopNanoTime - startNanoTime)));

        return returnObject;
    }
}


