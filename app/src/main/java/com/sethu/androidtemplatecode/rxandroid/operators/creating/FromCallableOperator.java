package com.sethu.androidtemplatecode.rxandroid.operators.creating;

import java.util.ArrayList;

import io.reactivex.Observable;

//From Callable operator convert various other objects and data types into Observables

public class FromCallableOperator {
    private static ArrayList list = new ArrayList();

    private static void addElements() {
        list.add("joice");
        list.add("abc");
        list.add("xyz");
        list.add("callable operator");
    }

    public static ArrayList getList() {
        return list;
    }

    public static Observable<Object> fromCallableOperator() {
        addElements();
        return Observable.fromIterable(list);
    }

}

//place this code in onCreate method of RxAndroid2DemoActivity to watch the output
//     FromCallableOperator.fromCallableOperator().subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Observer<Object>() {
//        @Override
//        public void onSubscribe(Disposable d) {
//
//        }
//
//        @Override
//        public void onNext(Object o) {
//            textView.append("  "+o);
//        }
//
//        @Override
//        public void onError(Throwable e) {
//
//        }
//
//        @Override
//        public void onComplete() {
//
//        }
//    });


