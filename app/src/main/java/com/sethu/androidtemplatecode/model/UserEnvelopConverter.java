package com.sethu.androidtemplatecode.model;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Nullable;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class UserEnvelopConverter extends Converter.Factory {
    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Type envelopeType = TypeToken.getParameterized(UserEnvelop.class,type).getType();

        final Converter<ResponseBody,UserEnvelop<?>> delegate =
                retrofit.nextResponseBodyConverter(this,envelopeType,annotations);

        return new Converter<ResponseBody, List<User>>() {
           @Override
           public List<User> convert(ResponseBody body) throws IOException {
               UserEnvelop<?> envelop = delegate.convert(body);
               return envelop.data;
           }
       };
    }
}

// using this converter class we get only "data" part of json response ignoring the rest
//url https://reqres.in/api/users
//{
//   "page":1,
//           "per_page":3,
//           "total":12,
//           "total_pages":4,
//           "data":[
//           {
//           "id":1,
//           "first_name":"George",
//           "last_name":"Bluth",
//           "avatar":"https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg"
//           },
//           ....
//           }