package com.sethu.androidtemplatecode.rxandroid.operators.creating;
import io.reactivex.Observable;

//just operator:- convert an object or a set of objects into an Observable that emits that
// or those objects

public class JustOperator {
    public static Observable getJustObservable(){
        return  Observable.just("item1","item2","item3");
    }
}

//place this code in onCreate method of RxAndroid2DemoActivity to watch the output
//        JustOperator.getJustObservable()
//                .observeOn(Schedulers.newThread())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer() {
//                    @Override
//                    public void onSubscribe(Disposable d) { }
//
//                    @Override
//                    public void onNext(Object o) {
//                            textView.append("  "+o);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                            textView.append(" " + e);
//                    }
//
//                    @Override
//                    public void onComplete() { }
//                });