package com.example.newhelloworld.util;

import android.view.LayoutInflater;

import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectUtil {

    public static <VB extends ViewBinding> VB getBinding(LayoutInflater inflater, Class<?> clazz){
        Type genericSuperclass = clazz.getGenericSuperclass();

        if(!(genericSuperclass instanceof ParameterizedType))throw new RuntimeException("父类非泛型");

        VB binding = null;
        Type actualTypeArgument = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        try {
            Class<VB> cl = (Class<VB>) actualTypeArgument;
            Method method = cl.getMethod("inflate", LayoutInflater.class);
            binding = (VB)method.invoke(null, inflater);
            return binding;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("no static method: inflate");
        }

    }

}
