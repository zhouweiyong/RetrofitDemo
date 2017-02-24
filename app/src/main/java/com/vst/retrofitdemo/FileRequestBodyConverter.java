package com.vst.retrofitdemo;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author zwy
 * @email 16681805@qq.com
 * created on 2017/2/23
 * class description:请输入类描述
 */
class FileRequestBodyConverterFactory extends Converter.Factory {
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new FileRequstBodyConverter();
    }

    static class FileRequstBodyConverter implements Converter<File, RequestBody> {

        @Override
        public RequestBody convert(File value) throws IOException {
            return RequestBody.create(MediaType.parse("image/jpeg"), value);
        }
    }
}
